package com.yanyu.sky.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.generator.bean.po.Template;
import com.yanyu.sky.generator.bean.po.TemplateDesc;
import com.yanyu.sky.generator.bean.vo.template.TemplateSaveVo;
import com.yanyu.sky.generator.bean.vo.template.TemplateSearchVo;
import com.yanyu.sky.generator.dao.TemplateDescMapper;
import com.yanyu.sky.generator.dao.TemplateMapper;
import com.yanyu.sky.generator.service.ITemplateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 模板信息 服务实现类
 * @author yanyu
 * @date 2020/11/18
 */
@Service
@AllArgsConstructor
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    private TemplateDescMapper templateDescMapper;

    @Override
    public IPage<Template> listPage(TemplateSearchVo vo) {
        return baseMapper.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()), Wrappers.<Template>lambdaQuery()
                .eq(Template::getTemplateGroupId,vo.getTemplateGroupId()).orderByDesc(Template::getUpdateTime));
    }

    @Override
    @Transactional
    public void add(TemplateSaveVo vo) {
        Template template = new Template();
        BeanUtils.copyProperties(vo, template);
        baseMapper.insert(template);
        TemplateDesc des = new TemplateDesc();
        des.setTemplateId(template.getId());
        des.setContent(vo.getContent());
        templateDescMapper.insert(des);
    }

    @Override
    @Transactional
    public void update(TemplateSaveVo vo) {
        Template template = new Template();
        BeanUtils.copyProperties(vo, template);
        baseMapper.updateById(template);
        TemplateDesc des = new TemplateDesc();
        des.setTemplateId(template.getId());
        des.setContent(vo.getContent());
        templateDescMapper.updateById(des);
    }
}
