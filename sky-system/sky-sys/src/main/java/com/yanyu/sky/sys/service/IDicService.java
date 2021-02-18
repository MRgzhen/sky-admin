package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.Dic;
import com.yanyu.sky.sys.bean.vo.dic.DicSaveVo;
import com.yanyu.sky.sys.bean.vo.dic.DicSearchVo;

/**
 * 数据字典 业务接口
 * @author yanyu
 */
public interface IDicService extends IService<Dic> {

    IPage<Dic> listPDicPage(DicSearchVo vo);

    void add(DicSaveVo vo);

    void update(DicSaveVo vo);

    void delete(IdsEntity vo);
}
