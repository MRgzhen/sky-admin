package com.yanyu.sky.sys.bean.vo.role;

import com.yanyu.sky.common.constant.SkyRegexConstant;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @author yanyu
 */
@Data
@ToString
public class RoleSaveVo {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class, UpdateEnabledGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "租户角色编码")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class, AddGroup.class})
    @Size(min = 1, max = 30, message = "{Size}",groups = {UpdateGroup.class,AddGroup.class})
    @Pattern(regexp = SkyRegexConstant.ALPHANUMERIC_UNDERLINE,message = "{Alphanumeric}",groups = {UpdateGroup.class,AddGroup.class})
    private String code;

    @ApiModelProperty(value = "租户角色名")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    @Size(min = 1, max = 30, message = "{Size}",groups = {UpdateGroup.class,AddGroup.class})
    private String name;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    @NotNull(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class,UpdateEnabledGroup.class})
    private EnabledType enabled;

    @ApiModelProperty(value = "用户类型")
    @NotNull(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private UserType type;
}
