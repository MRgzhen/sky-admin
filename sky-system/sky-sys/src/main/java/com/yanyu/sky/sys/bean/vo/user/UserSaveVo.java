package com.yanyu.sky.sys.bean.vo.user;

import com.yanyu.sky.common.constant.SkyRegexConstant;
import com.yanyu.sky.common.valid.Phone;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author yanyu
 */
@Data
@ToString
public class UserSaveVo {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "登录名")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    @Size(min = 1, max = 30, message = "{Size}",groups = {UpdateGroup.class,AddGroup.class})
    @Pattern(regexp = SkyRegexConstant.USERNAME,message = "{Alphanumeric}",groups = {UpdateGroup.class,AddGroup.class})
    private String userName;

    @ApiModelProperty(value = "昵称")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    @Size(min=1,max = 30,message = "{Size}",groups = {UpdateGroup.class,AddGroup.class})
    private String nickName;

    @ApiModelProperty(value = "性别，数据字典")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String sex;

    @ApiModelProperty(value = "生日")
    @Past(message = "{Post}",groups = {UpdateGroup.class,AddGroup.class})
    private Date birthday;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    @Phone(groups = {UpdateGroup.class,AddGroup.class})
    private String phone;

    @ApiModelProperty(value = "email地址")
    @Email(message = "{Email}",groups = {UpdateGroup.class,AddGroup.class})
    private String eamil;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class,AddGroup.class})
    private EnabledType enabled;

    @ApiModelProperty(value = "密码失效时间")
    @Future(message = "{Future}",groups = {AddGroup.class})
    private Date passwordValid;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "用户类型")
    private UserType type;

    @ApiModelProperty(value = "角色id")
    private List<String> roleIds;
}
