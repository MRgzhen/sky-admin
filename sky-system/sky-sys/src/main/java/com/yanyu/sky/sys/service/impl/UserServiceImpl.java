package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.util.AssertUtil;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.enums.IsSysType;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.po.Role;
import com.yanyu.sky.sys.bean.po.RoleUser;
import com.yanyu.sky.sys.bean.po.User;
import com.yanyu.sky.sys.bean.vo.user.UserInfoVo;
import com.yanyu.sky.sys.bean.vo.user.UserSaveVo;
import com.yanyu.sky.sys.bean.vo.user.UserSearchVo;
import com.yanyu.sky.sys.bean.vo.user.UserToRolesVo;
import com.yanyu.sky.sys.dao.RoleMapper;
import com.yanyu.sky.sys.dao.RoleUserMapper;
import com.yanyu.sky.sys.dao.UserMapper;
import com.yanyu.sky.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统用户 业务实现类
 * @author yanyu
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<UserInfoVo> listUserPage(UserSearchVo vo) {
        IPage<UserInfoVo> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        return baseMapper.listUserPage(page, vo);
    }

    @Override
    @Transactional
    public void add(UserSaveVo vo) {
        User user = new User();
        BeanUtils.copyProperties(vo, user);
//        user.setIsSys(IsSysType.COMM);
        user.setPassword(passwordEncoder.encode(SysConstant.DEFAULT_INIT_PASSWOR));
        baseMapper.insert(user);

        // 重新分配角色
        if (CollectionUtils.isNotEmpty(vo.getRoleIds())) {
            roleUserMapper.delete(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getUserId, user.getId()));
            vo.getRoleIds().stream().forEach(roleId -> {
                roleUserMapper.insert(RoleUser.builder().userId(user.getId()).roleId(roleId).build());
            });
        }
    }

    @Override
    @Transactional
    public void update(UserSaveVo vo) {
        // 是否允许修改
        canUpdate(vo.getId());

        // 修改用户
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        baseMapper.updateById(user);

        // 不是系统用户可以分配角色
        if (CollectionUtils.isNotEmpty(vo.getRoleIds())) {
            User oldUser = baseMapper.selectById(vo.getId());
            if (!IsSysType.SUPER_ADMIN.equals(oldUser.getIsSys())) {
                roleUserMapper.delete(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getUserId, user.getId()));
                vo.getRoleIds().stream().forEach(roleId -> {
                    roleUserMapper.insert(RoleUser.builder().userId(user.getId()).roleId(roleId).build());
                });
            }
        }
    }

    @Override
    @Transactional
    public void delete(IdsEntity vo) {
        // 是否允许删除
        String id = vo.getIds().iterator().next();
        canUpdate(id);
        // 系统用户不能删除
        User oldUser = baseMapper.selectById(id);
        AssertUtil.notEquals(IsSysType.SUPER_ADMIN, oldUser.getIsSys(), "系统用户不能删除");
        // 删除用户
        baseMapper.deleteById(id);
        roleUserMapper.delete(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getUserId, id));
    }

    @Override
    public Set<String> userOfRoles(String userId) {
        List<Role> roles = roleMapper.listRolesByUserId(userId);
        Set<String> roleIds = roles.parallelStream().map(role -> role.getId()).collect(Collectors.toSet());
        return roleIds;
    }

    @Override
    @Transactional
    public void userToRoles(UserToRolesVo vo) {
        // 是否允许修改
        canUpdate(vo.getId());

        // 不是系统用户可以分配角色
        User oldUser = baseMapper.selectById(vo.getId());
        if (!IsSysType.SUPER_ADMIN.equals(oldUser.getIsSys())) {
            roleUserMapper.delete(Wrappers.<RoleUser>lambdaQuery().eq(RoleUser::getUserId, vo.getId()));
            vo.getRoleIds().stream().forEach(roleId -> {
                roleUserMapper.insert(RoleUser.builder().userId(vo.getId()).roleId(roleId).build());
            });
        }
    }

    private void canUpdate(String id) {
        User oldUser = baseMapper.selectById(id);
        AssertUtil.isNotNull(oldUser, "修改的用户不存在");
    }
}
