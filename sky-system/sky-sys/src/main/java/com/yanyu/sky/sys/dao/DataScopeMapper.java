package com.yanyu.sky.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanyu.sky.sys.bean.po.DataScope;
import com.yanyu.sky.sys.bean.po.Dept;

import java.util.List;

/**
 * 系统数据权限关系表 Mapper 接口
 * @author yanyu
 */
public interface DataScopeMapper extends BaseMapper<DataScope> {

    List<Dept> listDataScope(String roleId);

}
