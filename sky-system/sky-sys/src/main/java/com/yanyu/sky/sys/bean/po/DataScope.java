package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统角色部门关系表
 * @author yanyu
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("sys_datascope")
@ApiModel(value = "DataScope对象", description = "系统数据权限关系表")
public class DataScope implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "部门id")
    private String deptId;


}
