package com.yanyu.sky.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.sys.bean.po.Social;
import com.yanyu.sky.sys.bean.vo.social.SocialBindVo;
import com.yanyu.sky.sys.bean.vo.social.SocialOfUserInfoVo;
import com.yanyu.sky.sys.bean.vo.social.SocialOfUserVo;
import com.yanyu.sky.sys.bean.vo.social.SocialUnBindVo;
import com.yanyu.sky.sys.service.ISocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

/**
 * 功能: 社交 前端控制器
 * @author yanyu
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/sys/social")
@Api(tags = "社交")
@Slf4j
@Validated
public class SocialController {

    @Autowired
    private ISocialService socialService;

    @GetMapping("/list")
    @ApiOperation(value = "查询社交信息")
    public Result list(Integer pageNum, Integer pageSize, Social vo) {
        IPage<Social> page = socialService.listPage(pageNum, pageSize, vo);
        return Result.success(page);
    }

    @GetMapping("/list/userId")
    @ApiOperation(value = "根据用户id查询该用户关联的社交账号")
    public Result listByUserId(@NotEmpty(message = "用户信息为空")String userId) {
        List<SocialOfUserVo> apps = socialService.listByUserId(userId);
        return Result.success(apps);
    }

    @PostMapping("unbind")
    public Result unBind(@RequestBody SocialUnBindVo vo) {
        socialService.unbindSocial(vo);
        return Result.success();
    }

    @PostMapping("bind")
    public Result bind(@RequestBody SocialBindVo vo) {
        socialService.bindSocial(vo);
        return Result.success();
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增社交")
    public Result add(@RequestBody Social vo) {
        socialService.save(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改社交")
    public Result update(@RequestBody Social vo) {
        socialService.updateById(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除社交")
    public Result delete(@RequestBody String[] ids) {
        socialService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }
}