package com.yanyu.sky.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.generator.bean.constant.GeneratorConstant;
import com.yanyu.sky.generator.bean.po.Attr;
import com.yanyu.sky.generator.bean.po.TemplateGroup;
import com.yanyu.sky.generator.bean.vo.templateGroup.TemplateGroupSearchVo;
import com.yanyu.sky.generator.dao.AttrMapper;
import com.yanyu.sky.generator.dao.TemplateGroupMapper;
import com.yanyu.sky.generator.service.ITemplateGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 模板分组 服务实现类
 * @author yanyu
 * @date 2020/11/18
 */
@Service
public class TemplateGroupServiceImpl extends ServiceImpl<TemplateGroupMapper, TemplateGroup> implements ITemplateGroupService {

    @Autowired
    private AttrMapper attrMapper;

    @Override
    public IPage<TemplateGroup> listPage(TemplateGroupSearchVo vo) {
        return baseMapper.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()), Wrappers.<TemplateGroup>lambdaQuery().orderByAsc(TemplateGroup::getSort));
    }

    @Override
    @Transactional
    @Deprecated
    public void add(TemplateGroup vo) {
        baseMapper.insert(vo);
        List<Attr> attrs = attrMapper.selectList(Wrappers.<Attr>lambdaQuery().eq(Attr::getTemplateGroupId, GeneratorConstant.ATTR_DEFAULT));
        attrs.stream().forEach(attr -> {
            Attr newAttr = new Attr();
            newAttr.setTemplateGroupId(vo.getId());
            newAttr.setId(null);
            newAttr.setName(attr.getName());
            newAttr.setRemark(attr.getRemark());
            newAttr.setSort(attr.getSort());
            newAttr.setType(attr.getType());
            attrMapper.insert(newAttr);
        });
    }

    @Override
    @Transactional
    @Deprecated
    public void delete(IdsEntity ids) {
        ids.getIds().stream().forEach(id -> {
            baseMapper.deleteById(id);
            attrMapper.delete(Wrappers.<Attr>lambdaQuery().eq(Attr::getTemplateGroupId, id));
        });
    }
}
