package com.yanyu.sky.generator.service.impl;

import cn.hutool.db.ds.simple.SimpleDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.exception.ServiceException;
import com.yanyu.sky.generator.bean.po.DsSetting;
import com.yanyu.sky.generator.bean.vo.dsSetting.DsSettingSearcheVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.TableInfoVo;
import com.yanyu.sky.generator.dao.DsSettingMapper;
import com.yanyu.sky.generator.service.IDsSettingService;
import com.yanyu.sky.generator.util.DyncDataSourceUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库配置 服务实现类
 * @author yanyu
 * @date 2020/11/18
 */
@Service
@AllArgsConstructor
public class DsSettingServiceImpl extends ServiceImpl<DsSettingMapper, DsSetting> implements IDsSettingService {

    private DyncDataSourceUtil dsUtils;

    @Override
    public IPage<DsSetting> listPage(DsSettingSearcheVo vo) {
        return baseMapper.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()), Wrappers.<DsSetting>lambdaQuery());
    }

    @Override
    public void testDs(String id) {
        DsSetting dsSetting = baseMapper.selectById(id);
        DataSource ds = new SimpleDataSource(dsSetting.getUrl(), dsSetting.getUsername(), dsSetting.getPassword());
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            log.warn("数据库连接失败");
            throw new ServiceException("数据库连接失败",e);
        }
    }

    @Override
    public IPage<TableInfoVo> listDsTables(DsSettingSearcheVo vo) {
        // 1. 获取数据连接信息
        DsSetting dsSetting = baseMapper.selectById(vo.getDsId());

        // 2. 判断是否存在数据库，不存在则 新增数据连接
        dsUtils.dsCreateIfAbsent(dsSetting);

        // 3. 切换数据源
        DynamicDataSourceContextHolder.push(dsSetting.getName());
        IPage<TableInfoVo> result = baseMapper.listTableByDs(new Page<>(vo.getPageNum(),vo.getPageSize()));
        DynamicDataSourceContextHolder.poll();
        return result;
    }
}
