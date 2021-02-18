package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.Role;
import com.yanyu.sky.sys.bean.vo.role.*;

import java.util.List;
import java.util.Set;

/**
 * 系统角色 业务接口
 * @author yanyu
 */
public interface IRoleService extends IService<Role> {

    IPage<RoleInfoVo> listRolePage(RoleSearchVo vo);

    Set<String> delete(IdsEntity vo);

    List<OfRoleVo> roleOfMenus(String roleId);

    void roleToMenus(RoleToMenusVo vo);

    List<OfRoleVo> roleOfDataScopes(String roleId);

    void roleToDataScopes(RoleToDataScopeVo vo);
}
