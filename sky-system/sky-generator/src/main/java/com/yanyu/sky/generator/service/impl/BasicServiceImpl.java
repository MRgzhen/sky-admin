package com.yanyu.sky.generator.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yanyu.sky.generator.bean.po.DsSetting;
import com.yanyu.sky.generator.bean.po.TemplateGroup;
import com.yanyu.sky.generator.bean.vo.basic.BasicPair;
import com.yanyu.sky.generator.dao.DsSettingMapper;
import com.yanyu.sky.generator.dao.TemplateGroupMapper;
import com.yanyu.sky.generator.service.IBasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 下拉列表 业务实现类
 * @author yanyu
 * @date 2020/11/18
 */
@Service
@Slf4j
public class BasicServiceImpl implements IBasicService {

    @Autowired
    private TemplateGroupMapper templateGroupMapper;
    @Autowired
    private DsSettingMapper dsSettingMapper;

    @Override
    public List<BasicPair> listTemplateGroup() {
        List<TemplateGroup> list = templateGroupMapper.selectList(Wrappers.<TemplateGroup>lambdaQuery().orderByAsc(TemplateGroup::getSort).orderByDesc(TemplateGroup::getUpdateTime));
        List<BasicPair> pairs = list.stream().map(item -> {
            return BasicPair.builder().value(item.getId()).label(item.getName()).build();
        }).collect(Collectors.toList());
        return pairs;
    }

    @Override
    public List<BasicPair> listDsSetting() {
        List<DsSetting> list = dsSettingMapper.selectList(Wrappers.<DsSetting>lambdaQuery().orderByDesc(DsSetting::getUpdateTime));
        List<BasicPair> pairs = list.stream().map(item -> {
            return BasicPair.builder().value(item.getId()).label(item.getNickName()).build();
        }).collect(Collectors.toList());
        return pairs;
    }
}
