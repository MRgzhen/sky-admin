package com.yanyu.sky.sys.bean.vo.social;

import lombok.*;

/**
 * @author yanyu
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SocialAuthVo {
    /** 用户id */
    private String userId;

    /** 是否已经关联, 0未关联，1已关联 */
    private String auth;

    /** 头像地址 */
    private String avatar;

    /** 昵称 */
    private String nickName;

    /** 当前第三方平台 */
    private String app;

    /** openId */
    private String openId;

    /** 临时参数 */
    private String tmpCode;
}
