package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.User;
import com.yanyu.sky.sys.bean.vo.user.UserInfoVo;
import com.yanyu.sky.sys.bean.vo.user.UserSaveVo;
import com.yanyu.sky.sys.bean.vo.user.UserSearchVo;
import com.yanyu.sky.sys.bean.vo.user.UserToRolesVo;

import java.util.Set;

/**
 * 系统用户 业务接口
 * @author yanyu
 */
public interface IUserService extends IService<User> {

    IPage<UserInfoVo> listUserPage(UserSearchVo vo);

    void add(UserSaveVo vo);

    void update(UserSaveVo vo);

    void delete(IdsEntity vo);

    Set<String> userOfRoles(String userId);

    void userToRoles(UserToRolesVo vo);

}
