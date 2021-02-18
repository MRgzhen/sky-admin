package com.yanyu.sky.generator.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.generator.bean.po.TemplateGroup;
import com.yanyu.sky.generator.bean.vo.templateGroup.TemplateGroupSearchVo;
import com.yanyu.sky.generator.config.annotation.YanyuEncryption;
import com.yanyu.sky.generator.service.ITemplateGroupService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * 模板分组 前端控制器
 * @author yanyu
 * @date 2020/12/1
 */
@RestController
@Validated
@RequestMapping("/generator/templateGroup")
public class TemplateGroupController {

    @Autowired
    private ITemplateGroupService service;

    @RequestMapping("/list")
    @ApiOperation(value = "查询模板分组信息")
    public Result list(TemplateGroupSearchVo vo) {
        IPage<TemplateGroup> page = service.listPage(vo);
        return Result.success(page);
    }

    @GetMapping("/check/name")
    @ApiOperation(value = "组名是否存在")
    public Result checkName(String id, @NotEmpty(message = "组名为空") String name) {
        int cnt = service.count(Wrappers.<TemplateGroup>lambdaQuery().ne(StringUtils.isNotBlank(id),TemplateGroup::getId,id).eq(TemplateGroup::getName, name));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增模板分组")
    public Result add(@RequestBody TemplateGroup vo) {
        service.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改模板分组")
    public Result update(@RequestBody TemplateGroup vo) {
        service.updateById(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除模板分组")
    public Result delete(@RequestBody IdsEntity ids) {
        service.removeByIds(ids.getIds());
        return Result.success();
    }
}

