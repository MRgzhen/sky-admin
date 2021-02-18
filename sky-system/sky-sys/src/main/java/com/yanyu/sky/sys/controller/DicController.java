package com.yanyu.sky.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.mrgzhen.core.util.AssertUtil;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.po.Dic;
import com.yanyu.sky.sys.bean.vo.dic.DicSaveVo;
import com.yanyu.sky.sys.bean.vo.dic.DicSearchVo;
import com.yanyu.sky.sys.service.IDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典 前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/dic")
@Api(tags = "系统字典")
public class DicController {

    @Autowired
    private IDicService dicService;

    @GetMapping("/list/p")
    @ApiOperation(value = "查询数据字典")
    public Result listP(DicSearchVo vo) {
        IPage<Dic> page = dicService.listPDicPage(vo);
        return Result.success(page);
    }

    @GetMapping("/get")
    @ApiOperation(value = "根据id数据字典项")
    public Result getDic(String id) {
        Dic dic = dicService.getById(id);
        return Result.success(dic);
    }

    @GetMapping("/check/code")
    @ApiOperation(value = "字典编码是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典id", dataType = "string", paramType = "query", example = "",required = true),
            @ApiImplicitParam(name = "code", value = "字典编码", dataType = "string", paramType = "query", example = "",required = true)
    })
    public Result checkCode(String id, String code) {
        int cnt = 0;
            AssertUtil.isNotNull(code, "字典编码不能为空");
            cnt = dicService.count(Wrappers.<Dic>lambdaQuery()
                    .ne(StringUtils.isNotBlank(id), Dic::getId, id)
                    .eq(Dic::getCode, code));
        return Result.success(cnt);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增数据字典")
    public Result add(@RequestBody @Validated(AddGroup.class) DicSaveVo vo) {
        dicService.add(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据字典")
    public Result update(@RequestBody @Validated(UpdateGroup.class) DicSaveVo vo) {
        dicService.update(vo);
        return Result.success();
    }
    @PostMapping("/update/enabled")
    @ApiOperation(value = "修改数据字典")
    public Result updateEnabled(@RequestBody @Validated(UpdateEnabledGroup.class) DicSaveVo vo) {
        dicService.update(Wrappers.<Dic>lambdaUpdate().set(Dic::getEnabled,vo.getEnabled()).eq(Dic::getId,vo.getId()));
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除数据字典")
    public Result delete(@RequestBody IdsEntity vo) {
        dicService.delete(vo);
        return Result.success();
    }
}

