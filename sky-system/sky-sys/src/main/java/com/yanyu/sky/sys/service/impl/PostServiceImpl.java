package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.sys.bean.po.Post;
import com.yanyu.sky.sys.dao.PostMapper;
import com.yanyu.sky.sys.service.IPostService;
import org.springframework.stereotype.Service;

/**
 * 职位 业务实现类
 * @author yanyu
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
