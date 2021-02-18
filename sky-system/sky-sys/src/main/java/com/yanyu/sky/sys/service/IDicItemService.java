package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.DicItem;
import com.yanyu.sky.sys.bean.vo.dic.DicItemSaveVo;
import com.yanyu.sky.sys.bean.vo.dic.DicItemSearchVo;

/**
 * 数据字典项 业务接口
 * @author yanyu
 */
public interface IDicItemService extends IService<DicItem> {

    IPage<DicItem> listCDicPage(DicItemSearchVo vo);

    void add(DicItemSaveVo vo);

    void update(DicItemSaveVo vo);

    void delete(IdsEntity vo);
}
