package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.Dept;
import com.yanyu.sky.sys.bean.vo.dept.DeptNodeVo;
import com.yanyu.sky.sys.bean.vo.dept.DeptSaveVo;
import com.yanyu.sky.sys.bean.vo.dept.DeptSearchVo;

/**
 * 部门 服务类
 * @author yanyu
 */
public interface IDeptService extends IService<Dept> {

    IPage<DeptNodeVo> listDeptPage(DeptSearchVo vo);
    void add(DeptSaveVo vo);
    void update(DeptSaveVo vo);
    void updateEnabled(DeptSaveVo vo);
    void delete(IdsEntity vo);
}
