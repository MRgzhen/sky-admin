package com.yanyu.sky.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.DataScopeType;
import com.yanyu.sky.sys.bean.po.Role;
import com.yanyu.sky.sys.bean.vo.role.*;
import com.yanyu.sky.sys.service.IMenuService;
import com.yanyu.sky.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 系统角色 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "系统角色")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @GetMapping("/list")
    @ApiOperation(value = "查询角色信息")
    public Result listRole(RoleSearchVo vo) {
        IPage<RoleInfoVo> page = roleService.listRolePage(vo);
        return Result.success(page);
    }

    @GetMapping("/check/code")
    @ApiOperation(value = "角色编码是否存在")
    public Result check(String id, String code) {
        Optional.ofNullable(code).orElseThrow(()->new ServiceException("角色编码为空"));
        int cnt = roleService.count(Wrappers.<Role>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), Role::getId, id).eq(Role::getCode, code));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增角色")
    public Result addRole(@RequestBody @Validated({AddGroup.class}) RoleSaveVo vo) {
        Role role = new Role();
        BeanUtils.copyProperties(vo, role);
        roleService.save(role);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改角色")
    public Result updateRole(@RequestBody @Validated({UpdateGroup.class}) RoleSaveVo vo) {
        Role role = new Role();
        BeanUtils.copyProperties(vo, role);
        roleService.updateById(role);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色")
    public Result deleteRole(@RequestBody IdsEntity vo) {
        Set<String> noDeleteNames = roleService.delete(vo);
        if(CollectionUtils.isNotEmpty(noDeleteNames)) {
            return Result.fail(String.format("角色{%s}已经被关联不能删除",StringUtils.join(noDeleteNames.toArray(), ",")));
        } else {
            return Result.success();
        }
    }

    @GetMapping("/ofMenus")
    @ApiOperation(value = "根据角色id查询菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "string", paramType = "query", example = "",required = true)
    })
    public Result ofMenus(@RequestParam("id") String roleId) {
        List<OfRoleVo> menuIds = roleService.roleOfMenus(roleId);
        return Result.success(menuIds);
    }

    @PostMapping("/toMenus")
    @ApiOperation(value = "角色分配菜单")
    public Result toMenus(@RequestBody @Validated RoleToMenusVo vo) {
        roleService.roleToMenus(vo);
        return Result.success();
    }

    @GetMapping("/ofDataScope")
    @ApiOperation(value = "根据角色id查询数据权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "string", paramType = "query", example = "",required = true)
    })
    public Result ofPerms(@RequestParam("id") String roleId) {
        List<OfRoleVo> dataScopes = roleService.roleOfDataScopes(roleId);
        return Result.success(dataScopes);
    }

    @PostMapping("/toDataScope")
    @ApiOperation(value = "角色分配数据权限")
    public Result toMenus(@RequestBody @Validated RoleToDataScopeVo vo) {
        AssertUtil.isTrue(!(DataScopeType.CUSTOM.equals(vo.getDataScope()) && CollectionUtils.isEmpty(vo.getDeptIds())),"请选择自定义数据权限");
        roleService.roleToDataScopes(vo);
        return Result.success();
    }
}

