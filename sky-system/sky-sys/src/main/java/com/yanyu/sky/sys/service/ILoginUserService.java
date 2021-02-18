package com.yanyu.sky.sys.service;


import com.github.mrgzhen.security.principal.AuthUser;

/**
 * 登录 业务接口
 * @author yanyu
 */
public interface ILoginUserService {

    /**
     * 用户登陆
     * @param username 当前登陆用户
     * @return
     */
    AuthUser getLoginUser(String username);

    /**
     * 社交登录
     * @param openId openId
     * @param app
     * @return
     */
    AuthUser getLoginUser(String openId,String app);
}
