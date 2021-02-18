package com.yanyu.sky.sys.bean.vo.role;

import com.yanyu.sky.sys.bean.po.Role;
import lombok.Data;
import lombok.ToString;

/**
 * 角色分页查询返回实体类
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
public class RoleInfoVo extends Role {

    // 关联用户数
    private int userCnt;
}
