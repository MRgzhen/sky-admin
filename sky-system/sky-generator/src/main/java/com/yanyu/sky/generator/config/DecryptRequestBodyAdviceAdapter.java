package com.yanyu.sky.generator.config;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.github.mrgzhen.core.exception.ServiceException;
import com.yanyu.sky.generator.config.annotation.YanyuEncryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import sun.rmi.runtime.Log;

import java.io.*;
import java.lang.reflect.Type;

/**
 * @author yanyu
 */
@ControllerAdvice
@Slf4j
public class DecryptRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    private SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getMethod().isAnnotationPresent(YanyuEncryption.class)
                || methodParameter.getMethod().getDeclaringClass().isAnnotationPresent(YanyuEncryption.class) ;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {
        byte[] body = aes.decrypt(inToString(inputMessage.getBody()));
        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(body) ;
            }
        } ;
    }

    private String inToString(InputStream in) {
        try (InputStreamReader isr = new InputStreamReader(in);
             BufferedReader inputStreamReader = new BufferedReader(isr);){
            return inputStreamReader.readLine();
        } catch (IOException e) {
            throw new ServiceException("解密读取流信息异常",e);
        }
    }
}
