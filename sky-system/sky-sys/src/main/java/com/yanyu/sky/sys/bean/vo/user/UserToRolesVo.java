package com.yanyu.sky.sys.bean.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author yanyu
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserToRolesVo {

    @ApiModelProperty(value = "用户id")
    @NotEmpty(message = "{NotEmpty}")
    private String id;

    @ApiModelProperty(value = "角色id")
    @NotEmpty(message = "{NotEmpty}")
    private Set<String> roleIds;
}
