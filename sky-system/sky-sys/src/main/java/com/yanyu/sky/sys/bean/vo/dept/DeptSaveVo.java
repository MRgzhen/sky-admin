package com.yanyu.sky.sys.bean.vo.dept;

import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author yanyu
 */
@Data
@ToString
public class DeptSaveVo {

    @ApiModelProperty(value = "主键")
    @NotEmpty(message = "{NotEmpty}",groups = UpdateGroup.class)
    private String id;

    @ApiModelProperty(value = "父部门，0代表顶级部门")
    private String parentId;

    @ApiModelProperty(value = "部门名")
    private String deptName;

    @ApiModelProperty(value = "部门类型")
    private String type;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    private EnabledType enabled;

    @ApiModelProperty(value = "部门顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;
}
