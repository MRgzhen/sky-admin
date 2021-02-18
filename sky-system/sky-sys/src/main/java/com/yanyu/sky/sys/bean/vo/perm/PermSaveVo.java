package com.yanyu.sky.sys.bean.vo.perm;

import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yanyu
 */
@Data
@ToString
public class PermSaveVo {

    @ApiModelProperty(value = "主键")
    @NotBlank(message = "{NotEmpty}",groups = UpdateGroup.class)
    private String id;

    @ApiModelProperty(value = "菜单父id，0代表顶级菜单")
    private String parentId;

    @ApiModelProperty(value = "菜单编码")
    private String code;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "状态，1：正常，0：冻结")
    private EnabledType enabled;

    @ApiModelProperty(value = "菜单顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "类型，0:目录，1：菜单，2：按钮，3：其他")
    private MenuType type;

    @ApiModelProperty(value = "权限标识，模块:业务:功能，如：system:user:add")
    private String perms;
}
