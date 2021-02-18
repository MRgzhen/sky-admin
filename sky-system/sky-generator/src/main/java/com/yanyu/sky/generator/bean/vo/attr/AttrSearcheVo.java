package com.yanyu.sky.generator.bean.vo.attr;


import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author yanyu
 */
@Data
public class AttrSearcheVo extends PageEntity {

    @ApiModelProperty(value = "分组id")
    @NotEmpty(message = "{NotEmpty}")
    private String templateGroupId;

    @ApiModelProperty(value = "类型，0：全部，1：自定义，2：默认")
    private String type;

    @ApiModelProperty(value = "参数名字")
    private String name;
}
