package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.enums.DataScopeType;
import com.yanyu.sky.sys.bean.po.*;
import com.yanyu.sky.sys.bean.vo.role.*;
import com.yanyu.sky.sys.dao.*;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.enums.LeafType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import com.yanyu.sky.sys.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统角色 业务实现类
 * @author yanyu
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private DataScopeMapper dataScopeMapper;

    @Override
    public IPage<RoleInfoVo> listRolePage(RoleSearchVo vo) {
        IPage<RoleInfoVo> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        page =  baseMapper.listRolePage(page, vo);
        page.getRecords().stream().forEach(roleDto -> {
            roleDto.setUserCnt(roleUserMapper.selectCount(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getRoleId, roleDto.getId())));
        });
        return page;
    }

    @Override
    @Transactional
    public Set<String> delete(IdsEntity vo) {
        Set<String> ids = vo.getIds();
        Set<String> noDeleteIds = new HashSet<>();
        Set<String> deleteIds = new HashSet<>();
        ids.stream().forEach(id -> {
            int userCnt = roleUserMapper.selectCount(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getRoleId, id));
            if(userCnt> 0) {
                noDeleteIds.add(id);
            } else {
                deleteIds.add(id);
            }
        });
        // 删除角色菜单关联关系和角色
        if(CollectionUtils.isNotEmpty(deleteIds)) {
            deleteIds.stream().forEach(id -> {
                roleMenuMapper.delete(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, id));
                baseMapper.deleteById(id);
            });
        }
        // 查询不能删除角色名称
        if(CollectionUtils.isNotEmpty(noDeleteIds)) {
            return baseMapper.selectList(Wrappers.<Role>lambdaQuery().in(Role::getId, noDeleteIds))
                    .stream().map(Role::getName).collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }

    @Override
    public List<OfRoleVo> roleOfMenus(String roleId) {
        List<Menu> menus = menuMapper.listMenusByRoleIds(new HashSet<>(Arrays.asList(roleId)),
                Arrays.asList(MenuType.MENU,MenuType.CATALOG,MenuType.BUTTON,MenuType.OTHER),null);
        List<OfRoleVo> roleOfMenus = new ArrayList<>();
        // 遍历菜单
        initMenu(roleOfMenus,menus);
        return roleOfMenus;
    }

    @Override
    @Transactional
    public void roleToMenus(RoleToMenusVo vo) {
        roleMenuMapper.delete(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, vo.getId()));
        vo.getMenuIds().stream().forEach(menuId -> {
            if(!SysConstant.DEFAULT_PARENT_ID.equals(menuId)) {
                roleMenuMapper.insert(RoleMenu.builder().roleId(vo.getId()).menuId(menuId).build());
            }
        });
    }

    @Override
    public List<OfRoleVo> roleOfDataScopes(String roleId) {

        List<Dept> depts = dataScopeMapper.listDataScope(roleId);
        List<OfRoleVo> roleOfDataScopes = depts.stream().map(dept -> {
            OfRoleVo vo = new OfRoleVo();
            vo.setId(dept.getId());
            vo.setParentId(dept.getParentId());
            return vo;
        }).collect(Collectors.toList());
        // 遍历菜单
//        initDataScope(roleOfDataScopes,depts);
        return roleOfDataScopes;
    }

    @Override
    @Transactional
    public void roleToDataScopes(RoleToDataScopeVo vo) {
        Role role = new Role();
        role.setId(vo.getId());
        role.setDataScope(vo.getDataScope());
        baseMapper.updateById(role);

        dataScopeMapper.delete(Wrappers.<DataScope>lambdaQuery().eq(DataScope::getRoleId, vo.getId()));
        if(CollectionUtils.isNotEmpty(vo.getDeptIds()) && DataScopeType.CUSTOM.equals(vo.getDataScope())) {
            vo.getDeptIds().stream().forEach(deptId -> {
                if(!SysConstant.DEFAULT_PARENT_ID.equals(deptId)) {
                    dataScopeMapper.insert(DataScope.builder().roleId(vo.getId()).deptId(deptId).build());
                }
            });
        }
    }

    private void initMenu(List<OfRoleVo> roleOfMenus, List<Menu> menus) {
        List<Menu> curMenus = menus.parallelStream().filter(menu -> SysConstant.DEFAULT_PARENT_ID.equals(menu.getParentId())).collect(Collectors.toList());
        curMenus.stream().forEach(curMenu -> {
            createMenu(roleOfMenus, menus, curMenu);
        });
    }
    private void createMenu(List<OfRoleVo> roleOfMenus, List<Menu> menus, Menu curMenu) {
        List<Menu> curMenus = menus.parallelStream().filter(menu -> curMenu.getId().equals(menu.getParentId())).collect(Collectors.toList());
        roleOfMenus.add(OfRoleVo.builder().id(curMenu.getId()).parentId(curMenu.getParentId())
                .leaf(CollectionUtils.isNotEmpty(curMenus) ? LeafType.NO : LeafType.YES).build());
        curMenus.stream().forEach(menu -> {
            createMenu(roleOfMenus, menus, menu);
        });
    }
    @Deprecated
    private void initDataScope(List<OfRoleVo> roleOfDataScopes, List<Dept> depts) {
        List<Dept> curDepts = depts.parallelStream().filter(dept -> SysConstant.DEFAULT_PARENT_ID.equals(dept.getParentId())).collect(Collectors.toList());
        curDepts.stream().forEach(curDept -> {
            createDept(roleOfDataScopes, depts, curDept);
        });
    }
    @Deprecated
    private void createDept(List<OfRoleVo> roleOfDataScopes, List<Dept> depts, Dept curDept) {
        List<Dept> curDepts = depts.parallelStream().filter(dept -> curDept.getId().equals(dept.getParentId())).collect(Collectors.toList());
        roleOfDataScopes.add(OfRoleVo.builder().id(curDept.getId()).parentId(curDept.getParentId())
                .leaf(CollectionUtils.isNotEmpty(curDepts) ? LeafType.NO : LeafType.YES).build());
        curDepts.stream().forEach(dept -> {
            createDept(roleOfDataScopes, depts, dept);
        });
    }
}
