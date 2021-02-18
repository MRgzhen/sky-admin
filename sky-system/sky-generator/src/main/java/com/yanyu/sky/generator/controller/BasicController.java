package com.yanyu.sky.generator.controller;

import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.generator.bean.vo.basic.BasicPair;
import com.yanyu.sky.generator.service.IBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模板属性 前端控制器
 * @author yanyu
 * @date 2020/12/1
 */
@RestController
@RequestMapping("/generator/basic")
@Api(tags = "下拉列表基础数据")
public class BasicController {

    @Autowired
    private IBasicService basicService;

    @GetMapping("/templateGroup")
    @ApiOperation(value = "查询模板命名空间")
    public Result listTemplateGroup() {
        List<BasicPair> nodes = basicService.listTemplateGroup();
        return Result.success(nodes);
    }

    @GetMapping("/dsSetting")
    @ApiOperation(value = "查询数据库连接设置")
    public Result listDsSetting() {
        List<BasicPair> nodes = basicService.listDsSetting();
        return Result.success(nodes);
    }
}
