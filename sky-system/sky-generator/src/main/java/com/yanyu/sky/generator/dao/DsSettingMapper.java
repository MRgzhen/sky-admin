package com.yanyu.sky.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yanyu.sky.generator.bean.po.DsSetting;
import com.yanyu.sky.generator.bean.vo.dsSetting.ColumnInfoVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.TableInfoVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据库配置 Mapper 接口
 * @author yanyu
 * @date 2020/11/18
 */
public interface DsSettingMapper extends BaseMapper<DsSetting> {

    /**
     * 查询数据库下所有表结构
     * @param dsName 数据库名
     * @return
     */
    @Select("select table_name tableName, engine, table_comment tableComment from information_schema.tables\n" +
            "             where table_schema = (select database()) ")
    IPage<TableInfoVo> listTableByDs(IPage<TableInfoVo> page);

    /**
     * 根据表名查询表信息
     * @param tableName 表名
     * @return
     */
    @Select("select table_name tableName, engine, table_comment tableComment from information_schema.tables\n" +
            " where table_schema = (select database()) and table_name = #{tableName}")
    TableInfoVo getTableByName(String tableName);

    /**
     * 表列信息
     * @param tableName 表名
     * @return
     */
    @Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns\n" +
            "  where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
    List<ColumnInfoVo> listColumn(String tableName);


}
