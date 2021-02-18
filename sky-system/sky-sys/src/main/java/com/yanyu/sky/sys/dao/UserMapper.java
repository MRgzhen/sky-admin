package com.yanyu.sky.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yanyu.sky.sys.bean.po.User;
import com.yanyu.sky.sys.bean.vo.user.UserInfoVo;
import com.yanyu.sky.sys.bean.vo.user.UserSearchVo;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户 Mapper 接口
 * @author yanyu
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<UserInfoVo> listUserPage(IPage<UserInfoVo> page, @Param("vo") UserSearchVo vo);
}
