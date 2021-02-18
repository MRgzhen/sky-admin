package com.yanyu.sky.sys.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.github.mrgzhen.core.exception.ServiceException;
import com.yanyu.sky.sys.bean.enums.SocialSexType;
import com.yanyu.sky.sys.bean.po.Social;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.model.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author yanyu
 */
@Component
public class SocialUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long DOMAIN_TIMEOUT_MINUTES = 3L;
    private static final long TMPCODE_TIMEOUT_MINUTES = 3L;
    private static final String AES_KEY = "0CTJUm6Qyw8W7jud";

    private static final String html = "<!DOCTYPE html>\n" +
            "<html xmlns:th=\"http://www.thymeleaf.org\" >\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>welcome</title>\n" +
            "    <script>\n" +
            "\n" +
            "        window.onload = function () {\n" +
            "            let data = '%s';\n" +
            "            let domain = '%s';\n" +
            "            window.opener.postMessage(data, domain);\n" +
            "            window.close();\n" +
            "        }\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div style=\"text-align: center;\"></div>\n" +
            "</body>\n" +
            "</html>";

    /**
     * 跳转地址
     * @param response 响应
     * @param data 数据
     * @param domain 地址
     */
    public void toDomain(HttpServletResponse response, String data, String domain) {
        String convertHtml = String.format(html, data, domain);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        try (PrintWriter pw = response.getWriter()) {
            pw.write(convertHtml);
        } catch (IOException e) {
            throw new ServiceException("请求异常",e);
        }
    }

    /**
     * 缓存跳转地址
     * @param app
     * @param state
     * @param domain
     */
    public void putDomain(String app, String state, String domain) {
        redisTemplate.opsForValue().set(domainKey(app, state), domain, DOMAIN_TIMEOUT_MINUTES, TimeUnit.MINUTES);
    }


    /**
     * 获取跳转地址
     * @param app
     * @param state
     */
    public String getDomain(String app, String state) {
        String domain = redisTemplate.opsForValue().get(domainKey(app, state));
        redisTemplate.delete(domainKey(app, state));
        return domain;
    }

    /**
     * 获取openid，不同第三方存在不一样
     * @param authResponse
     */
    public Social socialBuilder(AuthUser authUser) {
        String gender = authUser.getGender().getCode();
        Social social = Social.builder().accessToken(authUser.getToken().getAccessToken())
                .refreshToken(authUser.getToken().getRefreshToken())
                .expireTime(Integer.toUnsignedLong(authUser.getToken().getExpireIn()))
                .app(StringUtils.lowerCase(authUser.getSource()))
                .avatar(authUser.getAvatar())
                .nickName(authUser.getNickname())
                .sex("1".equals(gender) ? SocialSexType.MALE: "0".equals(gender) ? SocialSexType.FEMALE: SocialSexType.UNKNOWN)
                .company(authUser.getCompany())
                .address(authUser.getLocation())
                .remark(authUser.getRemark()).build();
        if(AuthDefaultSource.GITEE.name().equalsIgnoreCase(authUser.getSource())) {
            social.setOpenId(encryOpenId(authUser.getUuid()));
        }
        return social;
    }

    /**
     * 临时参数，用来校验当前请求前后openId等参数
     * @param app
     * @param openId
     * @return
     */
    public String putAndGetTmpCode(String app, String openId) {
        String tmpCode = System.currentTimeMillis()+ IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(tmpCode(app, tmpCode), openId, TMPCODE_TIMEOUT_MINUTES, TimeUnit.MINUTES);
        return tmpCode;
    }

    /**
     * 获取openId得知
     * @param app
     * @param tmpCode
     * @return
     */
    public String getTmpCode(String app, String tmpCode) {
        String value = redisTemplate.opsForValue().get(tmpCode(app, tmpCode));
        return value;
    }

    /**
     * 删除缓存数据
     * @param app
     * @param tmpCode
     */
    public void delTmpCode(String app, String tmpCode) {
        redisTemplate.delete(tmpCode(app, tmpCode));
    }

    /**
     * 对open进行加密
     * @param openId
     * @return
     * @throws UnsupportedEncodingException
     */
    public String encryOpenId(String openId) {
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), AES_KEY.getBytes()).getEncoded();
        AES aes = SecureUtil.aes(key);
        return aes.encryptHex(openId);
    }

    /**
     * 解密操作
     * @param decryOpenId
     * @return
     */
    public String decryOpenId(String decryOpenId) {
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), AES_KEY.getBytes()).getEncoded();
        AES aes = SecureUtil.aes(key);
        return aes.decryptStr(decryOpenId);
    }

    private String domainKey(String app, String state) {
        return StringUtils.join(app, ":state", state);
    }

    public String tmpCode(String app, String tmpCode) {
        return  StringUtils.join(app, ":tmpCode:", tmpCode);
    }
}
