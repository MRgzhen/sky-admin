package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.sys.bean.po.DatascopeTest;

import java.util.List;

/**
 * 数据权限测试 服务类
 * @author yanyu
 */
public interface IDatascopeTestService extends IService<DatascopeTest> {

    List<DatascopeTest> listPage();
}
