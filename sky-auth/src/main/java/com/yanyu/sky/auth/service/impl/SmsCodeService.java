package com.yanyu.sky.auth.service.impl;

import com.github.mrgzhen.security.valid.code.ValidCodeType;
import com.github.mrgzhen.security.valid.code.service.ValidCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yanyu
 */
@Service
@Slf4j
public class SmsCodeService implements ValidCodeService {

    private final static String REQUEST_ID = "requestId";

    private final static String CODE_PARAM = "code";

    @Override
    public void generatorAndSend(ServletWebRequest servletWebRequest) {

    }

    @Override
    public void validCode(ServletWebRequest servletWebRequest) {

    }

    @Override
    public ValidCodeType name() {
        return ValidCodeType.SMS;
    }
}
