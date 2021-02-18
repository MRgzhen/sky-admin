package com.yanyu.sky.auth.service.impl;

import com.github.mrgzhen.security.tokenGranter.authentication.SmsUserDetailsService;
import com.github.mrgzhen.security.tokenGranter.authentication.SocialUserDetailService;
import com.yanyu.sky.auth.feign.ISysClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author yanyu
 * @date 2021/1/25
 */
@Service
@Slf4j
public class SmsUserDetailService  implements SmsUserDetailsService {

    @Autowired
    private ISysClient sysClient;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return null;
    }
}
