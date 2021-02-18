package com.yanyu.sky.sys.controller;


import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.DatascopeTest;
import com.yanyu.sky.sys.service.IDatascopeTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据权限测试 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/dsTest")
@Api(tags = "数据权限测试")
public class DatascopeTestController {

    @Autowired
    private IDatascopeTestService service;

    @GetMapping("/list")
    @ApiOperation(value = "查询数据权限测试信息")
    public Result list() {
        List<DatascopeTest> page = service.listPage();
        return Result.success(page);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增数据权限测试")
    public Result add(@RequestBody DatascopeTest vo) {
        service.save(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据权限测试")
    public Result update(@RequestBody DatascopeTest vo) {
        service.updateById(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除数据权限测试")
    public Result delete(@RequestBody IdsEntity ids) {
        service.removeByIds(ids.getIds());
        return Result.success();
    }
}

