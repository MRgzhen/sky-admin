package com.yanyu.sky.generator.controller;

import com.yanyu.sky.common.util.WebUtil;
import com.yanyu.sky.generator.bean.vo.code.GeneratorCodeSearchVo;
import com.yanyu.sky.generator.service.IGeneratorCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成实现类
 * @author yanyu
 * @date 2020/11/18
 */
@RestController
@RequestMapping("/generator/code")
@Api(tags = "代码生成")
public class GeneratorCodeController {

    @Autowired
    private IGeneratorCodeService service;

    @PostMapping("/new")
    @ApiOperation(value = "代码生成")
    public void generatorCode(@RequestBody GeneratorCodeSearchVo vo, HttpServletResponse response) {
        byte[] data = service.generatorCode(vo);
        WebUtil.outStream(response, data, "sky");
    }
}
