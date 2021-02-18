package com.yanyu.sky.sys.bean.vo.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author yanyu
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleToPermsVo {

    @ApiModelProperty(value = "角色id")
    @NotEmpty(message = "{NotEmpty}")
    private String id;

    @ApiModelProperty(value = "菜单id")
    @NotEmpty(message = "{NotEmpty}")
    private String menuId;

    @ApiModelProperty(value = "权限id")
    @NotEmpty(message = "{NotEmpty}")
    private Set<String> permIds;
}
