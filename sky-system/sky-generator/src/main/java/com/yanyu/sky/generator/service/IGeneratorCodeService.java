package com.yanyu.sky.generator.service;


import com.yanyu.sky.generator.bean.vo.code.GeneratorCodeSearchVo;

/**
 * 代码生成
 * @author yanyu
 * @date 2020/12/1
 */
public interface IGeneratorCodeService {

    /**
     * 代码生成
     * @param vo
     */
    public byte[] generatorCode(GeneratorCodeSearchVo vo);
}
