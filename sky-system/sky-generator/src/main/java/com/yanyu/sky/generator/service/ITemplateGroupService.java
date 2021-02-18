package com.yanyu.sky.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.generator.bean.po.TemplateGroup;
import com.yanyu.sky.generator.bean.vo.templateGroup.TemplateGroupSearchVo;

/**
 * 模板分组 服务类
 * @author yanyu
 * @date 2020/11/18
 */
public interface ITemplateGroupService extends IService<TemplateGroup> {

    IPage<TemplateGroup> listPage(TemplateGroupSearchVo vo);

    void add(TemplateGroup vo);

    void delete(IdsEntity ids);
}
