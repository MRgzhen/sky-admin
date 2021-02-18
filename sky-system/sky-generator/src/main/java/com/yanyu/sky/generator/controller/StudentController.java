package com.yanyu.sky.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.mrgzhen.core.web.Result;
import com.yanyu.sky.generator.bean.po.Student;
import com.yanyu.sky.generator.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 功能:  前端控制器
 * @author yanyu
 * @date 2021-01-08
 */
@RestController
@RequestMapping("/generator/student")
@Api(tags = "")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/list")
    @ApiOperation(value = "查询信息")
    public Result list(Integer pageNum, Integer pageSize, Student vo) {
        IPage<Student> page = studentService.listPage(pageNum, pageSize, vo);
        return Result.success(page);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody Student vo) {
        studentService.save(vo);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Result update(@RequestBody Student vo) {
        studentService.updateById(vo);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody String[] ids) {
        studentService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }
}