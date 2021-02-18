package com.yanyu.sky.sys.bean.vo.social;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author yanyu
 */
@Data
@ToString
public class SocialUnBindVo {

    @NotEmpty(message = "不能为空")
    private String app;
}
