package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 系统角色用户关系表
 * @author yanyu
 */
@Data
@Builder
@ToString
@TableName("sys_role_user")
@ApiModel(value = "RoleUser对象", description = "系统角色用户关系表")
public class RoleUser extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "用户id")
    private String userId;


}
