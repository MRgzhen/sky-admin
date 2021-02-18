package com.yanyu.sky.generator.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.generator.bean.po.Template;
import com.yanyu.sky.generator.bean.po.TemplateDesc;
import com.yanyu.sky.generator.bean.vo.template.TemplateSaveVo;
import com.yanyu.sky.generator.bean.vo.template.TemplateSearchVo;
import com.yanyu.sky.generator.service.ITemplateDescService;
import com.yanyu.sky.generator.service.ITemplateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 模板信息 前端控制器
 * @author yanyu
 * @date 2020/12/1
 */
@RestController
@Validated
@RequestMapping("/generator/template")
public class TemplateController {

    @Autowired
    private ITemplateService service;
    @Autowired
    private ITemplateDescService descService;

    @RequestMapping("/list")
    @ApiOperation(value = "查询模板信息")
    public Result list(@Validated TemplateSearchVo vo) {
        IPage<Template> page = service.listPage(vo);
        return Result.success(page);
    }

    @RequestMapping("/get")
    @ApiOperation(value = "根据模板id查询模板内容")
    public Result get(@NotBlank(message = "模板id为空") String id) {
        TemplateDesc templateDesc = descService.getById(id);
        return Result.success(templateDesc);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增模板")
    public Result add(@RequestBody @Validated TemplateSaveVo vo) {
        service.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改模板")
    public Result update(@RequestBody @Validated TemplateSaveVo vo) {
        service.update(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除模板")
    public Result delete(@RequestBody IdsEntity ids) {
        service.removeByIds(ids.getIds());
        return Result.success();
    }
}

