package com.yanyu.sky.sys.controller;

import com.github.mrgzhen.core.security.LoginUser;
import com.github.mrgzhen.core.web.Result;
import com.github.mrgzhen.security.principal.AuthUser;
import com.yanyu.sky.common.constant.SkyAppConstant;
import com.yanyu.sky.sys.service.ILoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanyu
 */
@RestController
public class LoginUserController {

    @Autowired
    private ILoginUserService service;

    @GetMapping(SkyAppConstant.CLIENT_INTERNAL_CALL + "/login")
    public Result<LoginUser> getLoginUser(String username) {
        AuthUser authUser = service.getLoginUser(username);
        return Result.success(authUser);
    }

    @GetMapping(SkyAppConstant.CLIENT_INTERNAL_CALL + "/login/social")
    public Result<LoginUser> getSocialLoginUser(String openId,String app) {
        AuthUser authUser = service.getLoginUser(openId, app);
        return Result.success(authUser);
    }
}
