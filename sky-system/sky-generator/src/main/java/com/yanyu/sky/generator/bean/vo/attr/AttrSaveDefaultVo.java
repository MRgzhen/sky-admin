package com.yanyu.sky.generator.bean.vo.attr;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author yanyu
 * @date 2021/1/8
 */
@Data
public class AttrSaveDefaultVo {

    @ApiModelProperty(value = "分组id")
    @NotEmpty(message = "{NotEmpty}")
    private String templateGroupId;
}
