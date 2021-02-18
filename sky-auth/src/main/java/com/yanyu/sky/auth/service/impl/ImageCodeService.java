package com.yanyu.sky.auth.service.impl;


import com.github.mrgzhen.security.expection.ValidCodeException;
import com.github.mrgzhen.security.valid.code.ValidCodeType;
import com.github.mrgzhen.security.valid.code.service.ValidCodeService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yanyu
 */
@Slf4j
@Service
public class ImageCodeService implements ValidCodeService {

    private final static String REQUEST_ID = "requestId";

    private final static String CODE_PARAM = "code";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Producer producer;

    @Override
    public void generatorAndSend(ServletWebRequest servletWebRequest) {
        try {
            HttpServletResponse response = servletWebRequest.getResponse();
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setContentType("image/jpeg");

            //生成文字验证码
            String text = producer.createText();

            //个位数字相加
            String s1 = text.substring(0, 1);
            String s2 = text.substring(1, 2);
            int count = Integer.valueOf(s1).intValue() + Integer.valueOf(s2).intValue();

            //生成图片验证码
            BufferedImage image = producer.createImage(s1 + "+" + s2 + "=?");

            //保存 redis key 自己设置
            String requestId = servletWebRequest.getParameter(REQUEST_ID);
            stringRedisTemplate.opsForValue().set(requestId,String.valueOf(count), 3, TimeUnit.MINUTES);

            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validCode(ServletWebRequest servletWebRequest) {
        String codeInRequest = servletWebRequest.getParameter(CODE_PARAM);
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidCodeException("验证码的值不能为空");
        }

        String requestId = servletWebRequest.getParameter(REQUEST_ID);
        String codeInRedis = stringRedisTemplate.opsForValue().get(requestId);
        if (codeInRedis == null) {
            throw new ValidCodeException("验证码不存在");
        }

        if (!StringUtils.equals(codeInRedis, codeInRequest)) {
            throw new ValidCodeException("验证码不匹配");
        }

        stringRedisTemplate.delete(requestId);
    }

    @Override
    public ValidCodeType name() {
        return ValidCodeType.IMAGE;
    }
}
