package com.yanyu.sky.sys.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.po.Menu;
import com.yanyu.sky.sys.bean.vo.menu.MenuNodeVo;
import com.yanyu.sky.sys.bean.vo.menu.MenuSaveVo;
import com.yanyu.sky.sys.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 系统菜单 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "系统菜单")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/me")
    @ApiOperation(value = "查询当前登录用户权限菜单")
    public Result MenuMe() {
        List<MenuNodeVo> menuNodes = menuService.listLoginUserOfMenu();
        return Result.success(menuNodes);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有菜单列表")
    public Result listMenu() {
        List<MenuNodeVo> menuNodes = menuService.lisMenu();
        return Result.success(menuNodes);
    }

    @GetMapping("/check/perms")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "perms", value = "权限标识", dataType = "string", paramType = "query", example = "",required = true)
    })
    @ApiOperation(value = "权限标识是否存在")
    public Result checkCodePerms(String id, String perms) {
        Optional.ofNullable(perms).orElseThrow(()->new ServiceException("权限标识为空"));
        int cnt = menuService.count(Wrappers.<Menu>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), Menu::getId, id)
                .eq(Menu::getPerms, perms));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增菜单")
    public Result addMenu(@RequestBody @Validated({AddGroup.class}) MenuSaveVo vo) {
        menuService.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改菜单")
    public Result updateMenu(@RequestBody @Validated({UpdateGroup.class}) MenuSaveVo vo) {
        menuService.update(vo);
        return Result.success();
    }

    @PostMapping("/update/enabled")
    @ApiOperation(value = "修改菜单状态")
    public Result updateMenuEnabled(@RequestBody @Validated({UpdateGroup.class}) MenuSaveVo vo) {
        menuService.updateEnabled(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除菜单")
    public Result deleteMenu(@RequestBody IdsEntity vo) {
        menuService.delete(vo);
        return Result.success();
    }
}