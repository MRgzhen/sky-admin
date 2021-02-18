package com.yanyu.sky.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yanyu.sky.sys.bean.po.Role;
import com.yanyu.sky.sys.bean.vo.role.RoleInfoVo;
import com.yanyu.sky.sys.bean.vo.role.RoleSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色 Mapper 接口
 * @author yanyu
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> listRolesByUserIdAndTenantCodeNotLogin(@Param("userId") String userId, @Param("tenantCode") String tenantCode);

    List<Role> listRolesByUserId(String userId);

    IPage<RoleInfoVo> listRolePage(IPage<RoleInfoVo> page, @Param("vo") RoleSearchVo vo);
}
