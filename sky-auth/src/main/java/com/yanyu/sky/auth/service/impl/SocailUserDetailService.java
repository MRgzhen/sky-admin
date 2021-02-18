package com.yanyu.sky.auth.service.impl;

import com.github.mrgzhen.core.constant.AppConstant;
import com.github.mrgzhen.core.exception.AuthenticationException;
import com.github.mrgzhen.core.web.Result;
import com.github.mrgzhen.security.principal.AuthUser;
import com.github.mrgzhen.security.tokenGranter.authentication.SocialUserDetailService;
import com.yanyu.sky.auth.feign.ISysClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yanyu
 * @date 2021/1/21
 */
@Service
@Slf4j
public class SocailUserDetailService implements SocialUserDetailService {

    @Autowired
    private ISysClient sysClient;

    @Override
    public UserDetails loadUserByUsername(String openId, String app) throws UsernameNotFoundException {
        log.info("==> 用户：{}通过：{}在{}登录", openId, "", DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));

        Result<AuthUser> result = sysClient.getLoginSocialUser(openId, app);
        if(AppConstant.SUCCESS_RESPONSE.equals(result.getCode())) {
            AuthUser skyUser = result.getBean();
            if(skyUser == null) {
                throw new AuthenticationException("用户名/密码错误");
            }
            log.info("==> 登录成功，获取当前用户信息：{}", skyUser);
            return skyUser;
        }
        return null;
    }
}
