package com.yanyu.sky.sys.bean.vo.user;

import com.yanyu.sky.sys.bean.po.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
public class UserInfoVo extends User {

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "关联的角色")
    private String roleName;
}
