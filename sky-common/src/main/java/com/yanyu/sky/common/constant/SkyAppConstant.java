package com.yanyu.sky.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Sky系统实体类
 * @author yanyu
 * @date 2020/6/21
 */
public class SkyAppConstant {

    /** 基础 包路径 */
    public static final String BASE_PACKAGE = "com.yanyu.sky";
    /** feign 包路径 */
    public static final String FEIGN_BASE_PACKAGE = "com.yanyu.sky.*.feign";
    /** dao 包路径 */
    public static final String DAO_BASE_PACKAGE = "com.yanyu.sky.*.dao";
    /** service 包路径 */
    public static final String SERVICE_BASE_PACKAGE = "com.yanyu.sky.*.service.impl";

    /** auth 服务 */
    public static final String APPLICATION_OAUTH_NAME = "sky-auth";
    /** sys 服务 */
    public static final String APPLICATION_SYS_NAME = "sky-sys";

    /** 内部调用url，不需要认证 */
    public static final String CLIENT_INTERNAL_CALL = "/sky/anon/api";

    /** 用戶切換 token*/
    public static final String SWITCH_USER_ACCESS_TOKEN = "referece";

    /** 交换角色 */
    public static final String SWITCH_ROLE_ADMINISTRATOR = "ROLE_SWITCH_ADMINISTRATOR";

    public static final List<String> SOCIALS = Arrays.asList("weixin","qq","gitee","github","weibo");

}
