package com.yanyu.sky.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.generator.bean.po.TemplateDesc;
import com.yanyu.sky.generator.dao.TemplateDescMapper;
import com.yanyu.sky.generator.service.ITemplateDescService;
import org.springframework.stereotype.Service;

/**
 * 模板信息 服务实现类
 * @author yanyu
 * @date 2020/11/18
 */
@Service
public class TemplateDescServiceImpl extends ServiceImpl<TemplateDescMapper, TemplateDesc> implements ITemplateDescService {

}
