package com.yanyu.sky.sys.bean.vo.menu;

import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @author yanyu
 */
@Data
@ToString
public class MenuSaveVo {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class, UpdateEnabledGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "菜单父id，0代表顶级菜单")
    private String parentId;

    @ApiModelProperty(value = "菜单名")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class, AddGroup.class})
    @Size(min = 1, max = 30, message = "{Size}",groups = {UpdateGroup.class,AddGroup.class})
    private String name;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "类型，0:目录，1：菜单，2：按钮，3：其他")
    private MenuType type;

    @ApiModelProperty(value = "状态，1：正常，0：冻结")
    @NotNull(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class,UpdateEnabledGroup.class})
    private EnabledType enabled;

    @ApiModelProperty(value = "菜单顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "权限标识，模块:业务:功能，如：system:user:add")
    private String perms;
}
