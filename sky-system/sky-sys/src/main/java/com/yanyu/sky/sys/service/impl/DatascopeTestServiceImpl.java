package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.sys.bean.po.DatascopeTest;
import com.yanyu.sky.sys.dao.DatascopeTestMapper;
import com.yanyu.sky.sys.service.IDatascopeTestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据权限测试 服务实现类
 * @author yanyu
 */
@Service
public class DatascopeTestServiceImpl extends ServiceImpl<DatascopeTestMapper, DatascopeTest> implements IDatascopeTestService {

    @Override
    public List<DatascopeTest> listPage() {
        return baseMapper.selectList(Wrappers.<DatascopeTest>lambdaQuery());
    }
}
