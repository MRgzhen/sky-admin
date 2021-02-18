package com.yanyu.sky.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.mysql.cj.util.StringUtils;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.po.Dept;
import com.yanyu.sky.sys.bean.vo.dept.DeptNodeVo;
import com.yanyu.sky.sys.bean.vo.dept.DeptSaveVo;
import com.yanyu.sky.sys.bean.vo.dept.DeptSearchVo;
import com.yanyu.sky.sys.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * 部门 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "系统部门")
@Validated
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有部门列表")
    public Result listDept(DeptSearchVo vo) {
        IPage<DeptNodeVo> page = deptService.listDeptPage(vo);
        return Result.success(page);
    }

    @GetMapping("/check/deptName")
    @ApiOperation(value = "部门名称是否重复")
    public Result checkDeptName(String id, String deptName) {
        AssertUtil.isNotNull(deptName, "部门名称不能为空");
        int cnt = deptService.count(Wrappers.<Dept>lambdaQuery()
                .ne(!StringUtils.isNullOrEmpty(id), Dept::getId, id).eq(Dept::getDeptName, deptName));
        return Result.success(cnt);
    }

    @GetMapping("/get")
    @ApiOperation(value = "查询部门列表")
    public Result getDept(String id) {
        AssertUtil.isNotNull(id, "部门主键为空");
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增部门")
    public Result addDept(@RequestBody @Validated(AddGroup.class) DeptSaveVo vo) {
        deptService.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改部门")
    public Result updateDept(@RequestBody @Validated(UpdateGroup.class) DeptSaveVo vo) {
        deptService.update(vo);
        return Result.success();
    }

    @PostMapping("/update/enabled")
    @ApiOperation(value = "修改部门状态")
    public Result updateDeptEnabled(@RequestBody @Validated(UpdateGroup.class)  DeptSaveVo vo) {
        deptService.updateEnabled(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除部门")
    public Result deleteUser(@RequestBody IdsEntity vo) {
        deptService.delete(vo);
        return Result.success();
    }
}

