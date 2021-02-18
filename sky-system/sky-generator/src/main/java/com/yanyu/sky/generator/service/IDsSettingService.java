package com.yanyu.sky.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.generator.bean.po.DsSetting;
import com.yanyu.sky.generator.bean.vo.dsSetting.DsSettingSearcheVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.TableInfoVo;

/**
 * 数据库配置 服务类
 * @author yanyu
 * @date 2020/11/18
 */
public interface IDsSettingService extends IService<DsSetting> {

    IPage<DsSetting> listPage(DsSettingSearcheVo vo);

    void testDs(String id);

    /**
     * 查询数据库中的所有表
     * @param vo
     * @return
     */
    IPage<TableInfoVo> listDsTables(DsSettingSearcheVo vo);
}
