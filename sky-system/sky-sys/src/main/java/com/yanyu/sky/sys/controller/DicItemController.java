package com.yanyu.sky.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.po.DicItem;
import com.yanyu.sky.sys.bean.vo.dic.DicItemSaveVo;
import com.yanyu.sky.sys.bean.vo.dic.DicItemSearchVo;
import com.yanyu.sky.sys.service.IDicItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典项 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/dic/item")
@Api(tags = "系统字典项")
public class DicItemController {

    @Autowired
    private IDicItemService dicItemService;

    @GetMapping("/list/c")
    @ApiOperation(value = "查询数据字典项")
    public Result listC(DicItemSearchVo vo) {
        IPage<DicItem> page = dicItemService.listCDicPage(vo);
        return Result.success(page);
    }

    @GetMapping("/check/value")
    @ApiOperation(value = "字典项值是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典id", dataType = "string", paramType = "query", example = "",required = true),
            @ApiImplicitParam(name = "code", value = "字典编码", dataType = "string", paramType = "query", example = "",required = true),
            @ApiImplicitParam(name = "value", value = "字典项值", dataType = "string", paramType = "query", example = "",required = true)
    })
    public Result checkCode(String id, String code, String value) {
        int cnt = 0;
        AssertUtil.isNotNull(value, "字典项值不能为空");
        cnt = dicItemService.count(Wrappers.<DicItem>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), DicItem::getId, id)
                .eq(DicItem::getCode, code)
                .eq(DicItem::getValue, value));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增数据字典项")
    public Result add(@RequestBody @Validated(AddGroup.class) DicItemSaveVo vo) {
        dicItemService.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据字典项")
    public Result update(@RequestBody @Validated(UpdateGroup.class) DicItemSaveVo vo) {
        dicItemService.update(vo);
        return Result.success();
    }
    @PostMapping("/update/enabled")
    @ApiOperation(value = "修改数据字典项")
    public Result updateEnabled(@RequestBody @Validated(UpdateEnabledGroup.class) DicItemSaveVo vo) {
        dicItemService.update(Wrappers.<DicItem>lambdaUpdate().set(DicItem::getEnabled,vo.getEnabled()).eq(DicItem::getId,vo.getId()).eq(DicItem::getCode, vo.getCode()));
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除数据字典项")
    public Result delete(@RequestBody IdsEntity vo) {
        dicItemService.delete(vo);
        return Result.success();
    }
}

