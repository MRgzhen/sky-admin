package com.yanyu.sky.sys.bean.vo.perm;

import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanyu
 */
@Data
@ToString
public class PermSearchVo extends PageEntity {

    @ApiModelProperty(value = "父菜单id")
    private String menuId;

    @ApiModelProperty(value = "菜单编码")
    private String perm;

    @ApiModelProperty(value = "菜单名")
    private String name;
}
