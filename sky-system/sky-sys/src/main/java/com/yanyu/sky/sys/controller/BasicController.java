package com.yanyu.sky.sys.controller;

import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.sys.bean.vo.basic.BasicPair;
import com.yanyu.sky.sys.bean.vo.basic.BasicPairNode;
import com.yanyu.sky.sys.service.IBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 下拉列表  前端控制器
 * @author yanyu
 */
@RestController
@RequestMapping("/sys/basic")
@Api(tags = "下拉列表基础数据")
public class BasicController {

    @Autowired
    private IBasicService basicService;

    @GetMapping("/dept")
    @ApiOperation(value = "查询部门树")
    public Result listDept() {
        List<BasicPairNode> nodes = basicService.listDept();
        return Result.success(nodes);
    }
    @GetMapping("/role")
    @ApiOperation(value = "查询角色",notes = "id->value,name->label")
    public Result listRole() {
        List<BasicPair> pairs = basicService.listRole();
        return Result.success(pairs);
    }
    @GetMapping("/roleByType")
    @ApiOperation(value = "根据用户类型查询角色",notes = "id->value,name->label")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "type", value = "用户类型", dataType = "string", paramType = "query", example = "")
    )
    public Result listRoleByList(@RequestParam String type) {
        List<BasicPair> pairs = basicService.listRoleByType(type);
        return Result.success(pairs);
    }
    @GetMapping("/menuByType")
    @ApiOperation(value = "查询菜单树",notes = "查询类型为菜单和目录")
    public Result listMenuByType(String type) {
        List<BasicPairNode> pairs = basicService.listMenuByType(type);
        return Result.success(pairs);
    }
    @GetMapping("/menuWithPerm")
    @ApiOperation(value = "查询菜单树",notes = "查询类型为菜单、目录和按钮")
    public Result listMenuWithPerm() {
        List<BasicPairNode> pairs = basicService.listMenuWithPerm();
        return Result.success(pairs);
    }
    @GetMapping("/pDic")
    @ApiOperation(value = "查询数据字典项",notes = "id->value,name->label")
    public Result pdic() {
        List<BasicPair> pairs = basicService.listPDic();
        return Result.success(pairs);
    }
    @GetMapping("/childDicByCode")
    @ApiOperation(value = "根据数据字典编码查询数据子项",notes = "value->value,name->label")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "code", value = "字典编码", dataType = "string", paramType = "query", example = "")
    )
    public Result lisChildDicByCode(String code) {
        List<BasicPair> pairs = basicService.listChildDicByCode(code);
        return Result.success(pairs);
    }
}
