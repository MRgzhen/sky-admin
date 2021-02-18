package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.IsSysType;
import com.yanyu.sky.sys.bean.enums.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 系统用户
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "系统用户")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "性别，数据字典")
    private String sex;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "email地址")
    private String eamil;

    @ApiModelProperty(value = "是否是超级管理员用户；0：不是；1：是")
    private IsSysType isSys;

    @ApiModelProperty(value = "用户类型")
    private UserType type;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    private EnabledType enabled;

    @ApiModelProperty(value = "密码失效时间")
    private Date passwordValid;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "职位id")
    private String postId;

}
