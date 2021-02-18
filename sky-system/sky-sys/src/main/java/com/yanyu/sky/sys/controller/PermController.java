package com.yanyu.sky.sys.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.po.Menu;
import com.yanyu.sky.sys.bean.vo.perm.PermNodeVo;
import com.yanyu.sky.sys.bean.vo.perm.PermSaveVo;
import com.yanyu.sky.sys.bean.vo.perm.PermSearchVo;
import com.yanyu.sky.sys.service.IPermService;
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
 * 系统权限 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/perm")
@Api(tags = "系统权限")
public class PermController {

    @Autowired
    private IPermService permService;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有权限列表")
    public Result listPermByMenuId(PermSearchVo vo) {
        List<PermNodeVo> menuNodes = permService.listPermByMenuId(vo);
        return Result.success(menuNodes);
    }

    @GetMapping("/check/perms")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "perms", value = "权限标识", dataType = "string", paramType = "query", example = "",required = true)
    })
    @ApiOperation(value = "权限标识是否存在")
    public Result checkPerms(String id, String perms) {
        Optional.ofNullable(perms).orElseThrow(()->new ServiceException("权限标识为空"));
        int cnt = permService.count(Wrappers.<Menu>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), Menu::getId, id)
                .eq(Menu::getPerms, perms));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增权限")
    public Result addMenu(@RequestBody @Validated(AddGroup.class) PermSaveVo vo) {
        permService.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改权限")
    public Result updateMenu(@RequestBody @Validated(UpdateGroup.class) PermSaveVo vo) {
        permService.update(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除权限")
    public Result deleteMenu(@RequestBody IdsEntity vo) {
        permService.delete(vo);
        return Result.success();
    }
}
