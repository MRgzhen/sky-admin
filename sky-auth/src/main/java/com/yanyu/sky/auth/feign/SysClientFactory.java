package com.yanyu.sky.auth.feign;

import com.github.mrgzhen.core.web.Result;
import com.github.mrgzhen.security.principal.AuthUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yanyu
 */
@Component
@Slf4j
public class SysClientFactory implements FallbackFactory<ISysClient> {

    @Override
    public ISysClient create(Throwable cause) {
        return new ISysClient() {
            @Override
            public Result<AuthUser> getLoginUser(String username) {
                log.warn("获取用户失败，服务降级:{}",cause.getMessage());
                return Result.success();
            }

            @Override
            public Result<AuthUser> getLoginSmsUser(String phone) {
                log.warn("手机登录获取用户失败，服务降级:{}",cause.getMessage());
                return Result.success();
            }

            @Override
            public Result<AuthUser> getLoginSocialUser(String openId, String app) {
                log.warn("第三发授权登录获取用户失败，服务降级:{}",cause.getMessage());
                return Result.success();
            }
        };
    }
}
