package com.yanyu.sky.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.generator.bean.po.Template;
import com.yanyu.sky.generator.bean.vo.template.TemplateSaveVo;
import com.yanyu.sky.generator.bean.vo.template.TemplateSearchVo;

/**
 * 模板信息 服务类
 * @author yanyu
 * @date 2020/11/18
 */
public interface ITemplateService extends IService<Template> {

    IPage<Template> listPage(TemplateSearchVo vo);

    void add(TemplateSaveVo vo);

    void update(TemplateSaveVo vo);
}
