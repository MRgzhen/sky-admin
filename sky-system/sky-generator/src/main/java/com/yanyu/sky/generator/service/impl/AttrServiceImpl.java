package com.yanyu.sky.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.generator.bean.constant.GeneratorConstant;
import com.yanyu.sky.generator.bean.enums.AttrType;
import com.yanyu.sky.generator.bean.po.Attr;
import com.yanyu.sky.generator.bean.vo.attr.AttrSearcheVo;
import com.yanyu.sky.generator.dao.AttrMapper;
import com.yanyu.sky.generator.service.IAttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板属性 服务实现类
 * @author yanyu
 * @date 2020/11/18
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Override
    public List<Attr> list(AttrSearcheVo vo) {
        // 0：全部，1：自定义，2：默认
        LambdaQueryWrapper<Attr> wrapper = Wrappers.<Attr>lambdaQuery().like(StringUtils.isNotBlank(vo.getName()), Attr::getName, vo.getName()).orderByDesc(Attr::getType).orderByAsc(Attr::getSort);
        if(StringUtils.isBlank(vo.getType()) || "2".equals(vo.getType())) {
            wrapper.eq(Attr::getTemplateGroupId, GeneratorConstant.ATTR_DEFAULT);
        } else if("0".equals(vo.getType())) {
            wrapper.eq(Attr::getTemplateGroupId, vo.getTemplateGroupId())
                    .or().eq(Attr::getTemplateGroupId, GeneratorConstant.ATTR_DEFAULT);
        } else if("1".equals(vo.getType())){
            wrapper.eq(Attr::getTemplateGroupId, vo.getTemplateGroupId());
        }
        return  baseMapper.selectList(wrapper);
    }

    @Override
    public Map<String, List<Attr>> listTypeToGroup(AttrSearcheVo vo) {
        List<Attr> defaultAttrs = baseMapper.selectList(Wrappers.<Attr>lambdaQuery()
                .eq(Attr::getTemplateGroupId, GeneratorConstant.ATTR_DEFAULT).orderByAsc(Attr::getSort));

        List<Attr> customAttrs = baseMapper.selectList(Wrappers.<Attr>lambdaQuery()
                .eq(Attr::getTemplateGroupId, vo.getTemplateGroupId()).orderByAsc(Attr::getSort));

        Map<String, List<Attr>> result = new HashMap<>();
        result.put("custom",customAttrs);
        result.put("default",defaultAttrs);
        return result;
    }
}
