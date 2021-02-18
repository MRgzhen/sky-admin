package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 部门
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
@ApiModel(value = "Dept对象", description = "部门")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "父部门，0代表顶级部门")
    private String parentId;

    @ApiModelProperty(value = "父部门ids")
    private String parentIds;

    @ApiModelProperty(value = "部门名")
    private String deptName;

    @ApiModelProperty(value = "部门类型")
    private String type;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    private EnabledType enabled;

    @ApiModelProperty(value = "部门顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "层级")
    private String level;

    @ApiModelProperty(value = "备注")
    private String remark;


}
