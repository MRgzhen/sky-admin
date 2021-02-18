package com.yanyu.sky.generator.service;

import com.yanyu.sky.generator.bean.vo.basic.BasicPair;

import java.util.List;

/**
 * @author yanyu
 * @date 2020/11/20
 */
public interface IBasicService {

    List<BasicPair> listTemplateGroup();

    List<BasicPair> listDsSetting();
}
