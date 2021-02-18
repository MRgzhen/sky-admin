package com.yanyu.sky.generator.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.generator.bean.po.Attr;
import com.yanyu.sky.generator.bean.vo.attr.AttrSearcheVo;
import com.yanyu.sky.generator.config.annotation.YanyuEncryption;
import com.yanyu.sky.generator.service.IAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 模板属性 前端控制器
 * @author yanyu
 * @date 2020/12/1
 */
@RestController
@RequestMapping("/generator/attr")
@Api(tags = "模板属性")
@Validated
public class AttrController {

    @Autowired
    private IAttrService service;

    @RequestMapping("/list")
    @ApiOperation(value = "查询模板属性信息")
    public Result list(@Validated AttrSearcheVo vo) {
        List<Attr> list = service.list(vo);
        return Result.success(list);
    }

    @RequestMapping("/list/typeToGroup")
    @ApiOperation(value = "查询模板属性信息,按照类型分组")
    public Result listTypeToGroup(@Validated AttrSearcheVo vo) {
        Map<String, List<Attr>> result = service.listTypeToGroup(vo);
        return Result.success(result);
    }

    @GetMapping("/check/name")
    @ApiOperation(value = "参数名是否存在")
    public Result checkName(String id, String templateGroupId, String name ) {
        Optional.ofNullable(name).orElseThrow(()->new ServiceException("参数名为空"));
        Optional.ofNullable(templateGroupId).orElseThrow(()->new ServiceException("分组信息为空"));
        int cnt = service.count(Wrappers.<Attr>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), Attr::getId, id).eq(Attr::getName, name).eq(Attr::getTemplateGroupId, templateGroupId));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增模板属性")
    public Result add(@RequestBody Attr vo) {
        AssertUtil.isNotNull(vo.getTemplateGroupId(), "模板命名空间不能为空");
        service.save(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改模板属性")
    public Result update(@RequestBody Attr vo) {
        AssertUtil.isNotNull(vo.getTemplateGroupId(), "模板命名空间不能为空");
        service.updateById(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除模板属性")
    public Result delete(@RequestBody IdsEntity ids) {
        service.removeByIds(ids.getIds());
        return Result.success();
    }
}

