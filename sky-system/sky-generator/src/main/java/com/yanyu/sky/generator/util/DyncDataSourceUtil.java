package com.yanyu.sky.generator.util;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.yanyu.sky.generator.bean.po.DsSetting;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * @author yanyu
 * @date 2020/12/1
 */
@Slf4j
@Component
@AllArgsConstructor
public class DyncDataSourceUtil {

    private final DynamicRoutingDataSource ds;
    private final DataSourceCreator dataSourceCreator;

    public void addDs(String dsName, DsSetting dsSetting) {
       if(ds.getDataSource(dsName) != null) {
           synchronized (this) {
               if(ds.getDataSource(dsName) != null) {
                   DataSourceProperty dataSourceProperty = new DataSourceProperty();
                   dataSourceProperty.setUrl(dsSetting.getUrl());
                   dataSourceProperty.setUsername(dsSetting.getUsername());
                   dataSourceProperty.setPassword(dsSetting.getPassword());
                   dataSourceProperty.setDriverClassName(dsSetting.getDriver());
                   DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
                   ds.addDataSource(dsSetting.getName(), dataSource);
                   log.info("添加数据源成功，当前数据源信息:【{}】", StringUtils.join(ds.getCurrentDataSources().keySet(), ","));
               }
           }
       }
    }

    /**
     * 存在 true， 不存在 false
     * @param dsName
     * @return
     */
    public boolean existDs(String dsName) {
        return ds.getDataSource(dsName) != null;
    }

    /**
     * 没有则创建一个
     * @param dsName
     * @return
     */
    public void dsCreateIfAbsent(DsSetting dsSetting) {
        Optional.ofNullable(existDs(dsSetting.getName())).ifPresent(result -> {
            addDs(dsSetting.getName(), dsSetting);
        });
    }

    public void delDs(String dsName) {
        ds.removeDataSource(dsName);
    }
}
