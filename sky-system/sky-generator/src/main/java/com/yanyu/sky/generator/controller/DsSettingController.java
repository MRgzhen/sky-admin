package com.yanyu.sky.generator.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.generator.bean.po.DsSetting;
import com.yanyu.sky.generator.bean.vo.dsSetting.DsSettingSaveVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.DsSettingSearcheVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.TableInfoVo;
import com.yanyu.sky.generator.config.annotation.YanyuEncryption;
import com.yanyu.sky.generator.service.IDsSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * 数据库配置 前端控制器
 * @author yanyu
 * @date 2020/12/1
 */
@RestController
@RequestMapping("/generator/dsSetting")
@Api(tags = "数据源配置")
@Validated
public class DsSettingController {

    @Autowired
    private IDsSettingService service;

    @RequestMapping("/list")
    @ApiOperation(value = "查询数据源配置信息信息")
    public Result list(DsSettingSearcheVo vo) {
        IPage<DsSetting> page = service.listPage(vo);
        return Result.success(page);
    }

    @GetMapping("/check/nickName")
    @ApiOperation(value = "数据库昵称名字是否存在")
    public Result checkName(String id, @NotEmpty(message = "昵称为空") String nickName) {
        int cnt = service.count(Wrappers.<DsSetting>lambdaQuery().ne(StringUtils.isNotBlank(id),DsSetting::getId,id).eq(DsSetting::getNickName, nickName));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增数据源配置")
    public Result add(@RequestBody @Validated DsSettingSaveVo vo) {
        DsSetting dsSetting = new DsSetting();
        BeanUtils.copyProperties(vo, dsSetting);
        service.save(dsSetting);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据源配置")
    public Result update(@RequestBody @Validated DsSettingSaveVo vo) {
        DsSetting dsSetting = new DsSetting();
        BeanUtils.copyProperties(vo, dsSetting);
        service.updateById(dsSetting);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除数据源配置")
    public Result delete(@RequestBody IdsEntity ids) {
        service.removeByIds(ids.getIds());
        return Result.success();
    }

    @GetMapping("/test")
    @ApiOperation(value = "数据库连接测试")
    public Result test(String id) {
        service.testDs(id);
        return Result.success();
    }

    @GetMapping("/list/ds/tables")
    @ApiOperation(value = "查询数据库表")
    public Result listDsTables(DsSettingSearcheVo vo) {
        AssertUtil.isNotNull(vo.getDsId(), "请选择数据源");
        IPage<TableInfoVo> result = service.listDsTables(vo);
        return Result.success(result);
    }
}

