package com.yanyu.sky.generator.bean.vo.template;

import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 *
 * @author yanyu
 * @date 2020/11/18
 */
@Data
public class TemplateSaveVo {

    @ApiModelProperty(value = "模板id")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "分组id")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String templateGroupId;

    @ApiModelProperty(value = "模块包名")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String moduleName;

    @ApiModelProperty(value = "模板名")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String name;

    @ApiModelProperty(value = "模板描述")
    private String remark;

    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String content;
}
