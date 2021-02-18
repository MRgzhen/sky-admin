package com.yanyu.sky.sys.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yanyu.sky.sys.bean.po.Dept;
import com.yanyu.sky.sys.bean.vo.dept.DeptNodeVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 部门 Mapper 接口
 * @author yanyu
 */
public interface DeptMapper extends BaseMapper<Dept> {

    @Select("select * from sys_dept ${ew.customSqlSegment}")
    IPage<DeptNodeVo> listDeptPage(IPage<DeptNodeVo> page, @Param(Constants.WRAPPER) Wrapper<Dept> wrapper);
}
