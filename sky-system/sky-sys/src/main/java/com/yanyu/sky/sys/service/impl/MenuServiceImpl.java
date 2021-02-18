package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.security.LoginUser;
import com.github.mrgzhen.core.security.LoginUserContext;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.IsSysType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import com.yanyu.sky.sys.bean.po.Menu;
import com.yanyu.sky.sys.bean.po.Role;
import com.yanyu.sky.sys.bean.po.RoleMenu;
import com.yanyu.sky.sys.bean.vo.menu.MenuNodeVo;
import com.yanyu.sky.sys.bean.vo.menu.MenuSaveVo;
import com.yanyu.sky.sys.dao.MenuMapper;
import com.yanyu.sky.sys.dao.RoleMapper;
import com.yanyu.sky.sys.dao.RoleMenuMapper;
import com.yanyu.sky.sys.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统菜单 业务实现类
 * @author yanyu
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<MenuNodeVo> listLoginUserOfMenu() {
        LoginUser loginUser = LoginUserContext.getLoginUser();
        List<Menu> menus = new ArrayList<>();
        if (loginUser.getIsSys()) {
            menus = baseMapper.selectList(Wrappers.<Menu>lambdaQuery().in(Menu::getType, MenuType.CATALOG, MenuType.MENU)
                    .eq(Menu::getEnabled, EnabledType.ENABLED).orderByAsc(Menu::getSort));
        } else {
            if (CollectionUtils.isNotEmpty(loginUser.getRoles())) {
                Set<String> roleIds = roleMapper.selectList(Wrappers.<Role>lambdaQuery().in(Role::getCode, loginUser.getRoles()))
                        .stream().map(Role::getId).collect(Collectors.toSet());
                menus = baseMapper.listMenusByRoleIds(new HashSet<>(roleIds),
                        Arrays.asList(MenuType.CATALOG, MenuType.MENU), EnabledType.ENABLED.getValue());
            }
        }
        return initTree(menus);
    }

    @Override
    public List<MenuNodeVo> lisMenu() {
        List<Menu> menus = baseMapper.selectList(Wrappers.<Menu>lambdaQuery().orderByAsc(Menu::getSort));
        return initTree(menus);
    }

    @Override
    public void add(MenuSaveVo vo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(vo, menu);
        if (StringUtils.isBlank(vo.getParentId())) {
            // 没有传父id，默认为0
            menu.setParentId(SysConstant.DEFAULT_PARENT_ID);
        }
        // 设置菜单类型
        baseMapper.insert(menu);
    }

    @Override
    public void update(MenuSaveVo vo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(vo, menu);
        if (StringUtils.isBlank(vo.getParentId())) {
            // 没有传父id，默认为0
            menu.setParentId(SysConstant.DEFAULT_PARENT_ID);
        }

        // 判断父节点是否改变，改变->判断当前选中节点是否为该节点下子节点，如果是，则返回失败
        Menu curMenu = baseMapper.selectById(vo.getId());
        if (!curMenu.getParentId().equals(vo.getParentId())) {
            int result = assertChildNode(vo.getParentId(), vo.getId());
            if (result > 0) {
                throw new ServiceException("不能选择该节点下子节点");
            }
        }

        baseMapper.updateById(menu);
    }

    @Override
    public void updateEnabled(MenuSaveVo vo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(vo, menu);
        // 当菜单被禁用并且请求禁用启用标志和数据库不一致，则递归更新
        if (EnabledType.DISABLED.equals(vo.getEnabled())) {
            updateChild(menu);
        } else {
            baseMapper.updateById(menu);
        }
    }

    @Override
    public void delete(IdsEntity vo) {
        int roleCnt = assertChildMenuOfRole(vo.getIds().iterator().next());
        if (roleCnt > 0) {
            throw new ServiceException("菜单已被关联不能删除");
        }
        // 删除菜单及其子菜单
        deleteChildMenu(vo.getIds().iterator().next());
    }

    public List<MenuNodeVo> initTree(List<Menu> menus) {
        // 父类向子类转换
        List<MenuNodeVo> menuNodes = menus.parallelStream().map(menu -> {
            MenuNodeVo menuTree = new MenuNodeVo();
            BeanUtils.copyProperties(menu, menuTree);
            return menuTree;
        }).collect(Collectors.toList());

        // 创建树
        if (CollectionUtils.isNotEmpty(menuNodes)) {
            List<MenuNodeVo> topMenuNodes = menuNodes.parallelStream()
                    .filter(menuNode -> StringUtils.equals(SysConstant.DEFAULT_PARENT_ID, menuNode.getParentId()))
                    .collect(Collectors.toList());
            topMenuNodes.parallelStream().forEach(menuNode -> {
                createMenuTree(menuNode, menuNodes);
            });
            return topMenuNodes;
        }
        return null;
    }

    private void createMenuTree(MenuNodeVo curMenuNode, List<MenuNodeVo> allMenuNodes) {
        List<MenuNodeVo> nextMenuNodes = allMenuNodes.parallelStream()
                .filter(menuNode -> StringUtils.equals(curMenuNode.getId(), menuNode.getParentId()))
                .sorted().collect(Collectors.toList());
        nextMenuNodes.parallelStream().forEach(menuNode -> {
            createMenuTree(menuNode, allMenuNodes);
        });
        if (CollectionUtils.isNotEmpty(nextMenuNodes)) {
            curMenuNode.setChildren(nextMenuNodes);
        }
    }

    /**
     * 判断parentId是否是id的节点或id的子节点
     *
     * @param parentId
     * @param id
     * @return
     */
    private int assertChildNode(String parentId, String id) {
        if (StringUtils.equals(parentId, id)) {
            return 1;
        }

        List<Menu> childMenus = baseMapper.selectList(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, id).orderByAsc(Menu::getSort));
        int result = 0;
        for (int i = 0; i < childMenus.size(); i++) {
            result = assertChildNode(parentId, childMenus.get(i).getId());
            if (result > 0) {
                return result;
            }
        }
        return 0;
    }

    /**
     * 更新当前节点及其字节
     *
     * @param menu
     */
    private void updateChild(Menu menu) {
        baseMapper.updateById(menu);
        List<Menu> childMenus = baseMapper.selectList(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, menu.getId()).orderByAsc(Menu::getSort));
        childMenus.parallelStream().forEach(childMenu -> {
            Menu tmpMenu = new Menu();
            tmpMenu.setId(childMenu.getId());
            tmpMenu.setEnabled(menu.getEnabled());
            updateChild(tmpMenu);
        });
    }

    /**
     * 判断当前节点及其子节点是否有角色关联
     *
     * @param id
     */
    private int assertChildMenuOfRole(String id) {
        Integer roleCnt = roleMenuMapper.selectCount(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getMenuId, id));
        if (roleCnt > 0) {
            return roleCnt;
        }

        List<Menu> childrenMenu = baseMapper.selectList(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, id).orderByAsc(Menu::getSort));
        int result = 0;
        for (Menu menu : childrenMenu) {
            result = assertChildMenuOfRole(menu.getId());
            if (result > 0) {
                return result;
            }
        }
        return result;
    }

    /**
     * 删除当前节点及其字节
     * @param parentId
     */
    private void deleteChildMenu(String parentId) {
        baseMapper.deleteById(parentId);
        List<Menu> childMenus = baseMapper.selectList(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, parentId).orderByAsc(Menu::getSort));
        childMenus.parallelStream().forEach(menu -> {
            deleteChildMenu(menu.getId());
        });
    }
}
