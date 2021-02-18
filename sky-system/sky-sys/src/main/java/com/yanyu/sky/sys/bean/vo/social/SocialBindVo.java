package com.yanyu.sky.sys.bean.vo.social;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author yanyu
 */
@Data
@ToString
public class SocialBindVo {

    @NotEmpty(message = "不能为空")
    private String app;

    /** 唯一标识 */
    @NotEmpty(message = "不能为空")
    private String openId;

    /** 临时编码 */
    @NotEmpty(message = "不能为空")
    private String tmpCode;
}
