package com.yanyu.sky.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.generator.bean.po.Attr;
import com.yanyu.sky.generator.bean.vo.attr.AttrSearcheVo;

import java.util.List;
import java.util.Map;

/**
 * 模板属性 服务类
 * @author yanyu
 * @date 2020/11/18
 */
public interface IAttrService extends IService<Attr> {

    List<Attr> list(AttrSearcheVo vo);

    Map<String,List<Attr>> listTypeToGroup(AttrSearcheVo vo);
}
