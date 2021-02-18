package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统菜单
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "系统菜单")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "菜单父id，0代表顶级菜单")
    private String parentId;

    @ApiModelProperty(value = "菜单名")
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
    private EnabledType enabled;

    @ApiModelProperty(value = "菜单顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "层级")
    private String level;

    @ApiModelProperty(value = "权限标识，模块:业务:功能，如：system:user:add")
    private String perms;

}
