package com.yanyu.sky.sys.bean.vo.role;

import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
public class RoleSearchVo extends PageEntity {

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名")
    private String name;
}
