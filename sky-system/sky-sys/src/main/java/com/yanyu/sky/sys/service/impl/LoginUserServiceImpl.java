package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.security.LoginUser;
import com.github.mrgzhen.security.principal.AuthUser;
import com.yanyu.sky.sys.bean.enums.DataScopeType;
import com.yanyu.sky.sys.bean.enums.IsSysType;
import com.yanyu.sky.sys.bean.po.*;
import com.yanyu.sky.sys.dao.*;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import com.yanyu.sky.sys.service.ILoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanyu
 */
@Service
@Slf4j
public class LoginUserServiceImpl implements ILoginUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private SocialMapper socialMapper;
    @Autowired
    private DataScopeMapper roleDeptMapper;

    @Override
    public AuthUser getLoginUser(String username) {

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, username));
        AuthUser authUser = covert(user);
        if(user != null) {
            if (IsSysType.SUPER_ADMIN.equals(user.getIsSys())) {
                authUser.setRoles(new HashSet<>(Arrays.asList("ADMIN_ROLE")));
                List<Menu> perms = menuMapper.listMenusByRoleIds(null, Arrays.asList(MenuType.BUTTON, MenuType.OTHER), EnabledType.ENABLED.getValue());
            } else {
                List<Role> roles = roleMapper.listRolesByUserId(user.getId());
                authUser.setRoles(roles.stream().map(Role::getCode).collect(Collectors.toSet()));
                if(CollectionUtils.isNotEmpty(roles)) {
                    Set<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
                    List<Menu> perms = menuMapper.listMenusByRoleIds(roleIds, Arrays.asList(MenuType.BUTTON, MenuType.OTHER), EnabledType.ENABLED.getValue());
                }
            }
            return authUser;
        }
        return null;
    }

    @Override
    public AuthUser getLoginUser(String openId, String app) {
        Social social = socialMapper.selectOne(Wrappers.<Social>lambdaQuery().eq(Social::getOpenId, openId).eq(Social::getApp, app));
        User user = userMapper.selectById(social.getUserId());
        AuthUser authUser = getLoginUser(user.getUserName());
        return authUser;
    }

    private AuthUser covert(User user) {
        AuthUser authUser = null;
        if (IsSysType.SUPER_ADMIN.equals(user.getIsSys())) {
            authUser = new AuthUser(UUID.randomUUID().toString().replace("-",""), user.getUserName(), user.getPassword(),
                    true, true, true, true);
        } else {
            authUser = new AuthUser(UUID.randomUUID().toString().replace("-",""), user.getUserName(), user.getPassword(),
                    true, true, new Date().after(user.getPasswordValid()), "1".equals(user.getEnabled().getValue()));
        }

        authUser.setDeptId(user.getDeptId());
        authUser.setUserId(user.getId());
        authUser.setIsSys("1".equals(user.getIsSys().getValue()));
        return authUser;
    }
}
