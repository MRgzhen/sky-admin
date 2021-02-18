package com.yanyu.sky.auth.feign;


import com.github.mrgzhen.core.web.Result;
import com.github.mrgzhen.security.principal.AuthUser;
import com.yanyu.sky.common.constant.SkyAppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yanyu
 */
@FeignClient(value= SkyAppConstant.APPLICATION_SYS_NAME, fallbackFactory=SysClientFactory.class)
public interface ISysClient {

    @GetMapping(SkyAppConstant.CLIENT_INTERNAL_CALL + "/login")
    Result<AuthUser> getLoginUser(@RequestParam("username") String username);

    @GetMapping(SkyAppConstant.CLIENT_INTERNAL_CALL + "/login/sms")
    Result<AuthUser> getLoginSmsUser(@RequestParam("phone") String phone);

    @GetMapping(SkyAppConstant.CLIENT_INTERNAL_CALL + "/login/social")
    Result<AuthUser> getLoginSocialUser(@RequestParam("openId") String openId,@RequestParam("app")String app);

}
