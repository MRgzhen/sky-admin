package com.yanyu.sky.generator.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.util.JSONUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.generator.config.annotation.YanyuEncryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanyu
 * @date 2021/1/7,
 */
@ControllerAdvice
@Slf4j
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getMethod().isAnnotationPresent(YanyuEncryption.class)
                || returnType.getMethod().getDeclaringClass().isAnnotationPresent(YanyuEncryption.class) ;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return body ;
        }
        try {
            Result result = JSONUtil.instant().readValue(JSONUtil.instant().writeValueAsString(body), Result.class) ;
            Object bean = result.getBean();
            if(bean == null) {
                return result;
            }
             SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES);
            Map<String,String> encryptResult = new HashMap<>();
            encryptResult.put("key",aes.encryptBase64(JSONUtil.instant().writeValueAsString(result.getBean())));
            result.setBean(encryptResult);
            result.setCode("10001");
            return result;
        } catch (Exception e) {
            throw new ServiceException("加密异常",e) ;
        }
    }
}
