package com.yanyu.sky.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.security.LoginUser;
import com.github.mrgzhen.core.security.LoginUserContext;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.po.User;
import com.yanyu.sky.sys.bean.vo.user.UserInfoVo;
import com.yanyu.sky.sys.bean.vo.user.UserSaveVo;
import com.yanyu.sky.sys.bean.vo.user.UserSearchVo;
import com.yanyu.sky.sys.bean.vo.user.UserToRolesVo;
import com.yanyu.sky.sys.service.ILoginUserService;
import com.yanyu.sky.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 系统用户 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
@Api(tags = "系统用户")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginUserService service;

    @GetMapping("/user")
    @ApiOperation(value = "获取当前登录用户信息")
    public Result userMe() {
        LoginUser loginUser = LoginUserContext.getLoginUser();
        return Result.success(loginUser);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询用户")
    public Result listUser(UserSearchVo vo) {
        IPage<UserInfoVo> page = userService.listUserPage(vo);
        return Result.success(page);
    }

    @GetMapping("/check/code")
    @ApiOperation(value = "用户登录名是否存在")
    public Result check(String id, String userName) {
        AssertUtil.isNotNull(userName,"登录名为空");
        int cnt = userService.count(Wrappers.<User>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), User::getId, id).eq(User::getUserName, userName));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增用户")
    public Result addUser(@RequestBody @Validated(AddGroup.class) UserSaveVo vo) {
        userService.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户")
    public Result updateUser(@RequestBody @Validated(UpdateGroup.class) UserSaveVo vo) {
        userService.update(vo);
        return Result.success();
    }
    @PostMapping("/update/enabled")
    @ApiOperation(value = "修改用户状态")
    public Result updateEnabledUser(@RequestBody @Validated(UpdateEnabledGroup.class) UserSaveVo vo) {
        userService.update(new UpdateWrapper<User>().lambda().set(User::getEnabled,vo.getEnabled()).eq(User::getId,vo.getId()));
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户")
    public Result deleteUser(@RequestBody IdsEntity vo) {
        userService.delete(vo);
        return Result.success();
    }

    @GetMapping("/ofRoles")
    @ApiOperation(value = "查询用户关联的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", dataType = "int", paramType = "query", example = "",required = true)
    })
    public Result userOfRoles(@RequestParam("id") String userId) {
        Set<String> roleIds = userService.userOfRoles(userId);
        return Result.success(roleIds);
    }

    @PostMapping("/toRoles")
    @ApiOperation(value = "用户分配角色")
    public Result userToRoles(@RequestBody @Validated UserToRolesVo vo) {
        userService.userToRoles(vo);
        return Result.success();
    }
}

