package com.yanyu.sky.sys.bean.vo.role;

import com.yanyu.sky.sys.bean.enums.DataScopeType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author yanyu
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleToDataScopeVo {

    @ApiModelProperty(value = "角色id")
    @NotEmpty(message = "{NotEmpty}")
    private String id;

    @ApiModelProperty(value = "数据权限类型")
    @NotNull(message = "{NotNull}")
    private DataScopeType dataScope;

    @ApiModelProperty(value = "部门id")
    private Set<String> deptIds;
}
