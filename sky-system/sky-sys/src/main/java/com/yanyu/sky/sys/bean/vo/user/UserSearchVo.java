package com.yanyu.sky.sys.bean.vo.user;

import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author yanyu
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchVo extends PageEntity {

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "角色id")
    private String roleId;
}
