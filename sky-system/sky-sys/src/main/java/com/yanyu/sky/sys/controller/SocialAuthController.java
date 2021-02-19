package com.yanyu.sky.sys.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.util.JSONUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.sys.bean.vo.social.SocialAuthVo;
import com.yanyu.sky.sys.bean.vo.social.SocialBindVo;
import com.yanyu.sky.sys.bean.vo.social.SocialUnBindVo;
import com.yanyu.sky.sys.properties.SocialAuthProperties;
import com.yanyu.sky.sys.service.ISocialService;
import com.yanyu.sky.sys.util.SocialUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/social/auth")
@Slf4j
@ApiIgnore
public class SocialAuthController {

    @Autowired
    private ISocialService socialService;
    @Autowired
    private SocialUtil socialUtil;
    @Autowired
    private SocialAuthProperties socialAuthProperties;

    @RequestMapping("/login/{app}")
    @ApiIgnore
    public RedirectView login(@PathVariable("app") String app, @RequestParam("domain") String domain, HttpServletResponse response) throws JsonProcessingException {
        if(socialAuthProperties.getApp().get(app) == null) {
            // 关闭
            socialUtil.toDomain(response, JSONUtil.instant().writeValueAsString(Result.fail(new ServiceException())), domain);
            return new RedirectView();
        }

        AuthRequest authRequest = getAuthRequest(app);
        String state = AuthStateUtils.createState();
        socialUtil.putDomain(app, state, domain);
        return new RedirectView(authRequest.authorize(state));
    }

    @RequestMapping("/{app}/callback")
    @ApiIgnore
    public void callback(@PathVariable("app") String app, AuthCallback callback, HttpServletResponse response) throws JsonProcessingException {
       try {
           AuthRequest authRequest = getAuthRequest(app);
           AuthResponse authResponse = authRequest.login(callback);
           AuthUser authUser = JSONUtil.instant().convertValue(authResponse.getData(), AuthUser.class);

           SocialAuthVo vo = socialService.addAuth(authUser);
           String domain = socialUtil.getDomain(app, callback.getState());
           socialUtil.toDomain(response, JSONUtil.instant().writeValueAsString(Result.success(vo)), domain);
       } catch(Exception e) {
           log.error("第三方[{}]登录是失败=[{}]",app, e.getMessage(), e);
           String domain = socialUtil.getDomain(app, callback.getState());
           socialUtil.toDomain(response, JSONUtil.instant().writeValueAsString(Result.fail(new ServiceException())), domain);
       }
    }

    private AuthRequest getAuthRequest(String app) {
        SocialAuthProperties.App socialApp = socialAuthProperties.getApp().get(app);
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId(socialApp.getClientId())
                .clientSecret(socialApp.getClientSecret())
                .redirectUri(StringUtils.join(String.format(socialApp.getRedirectUrl(), app)))
                .build());
    }
}