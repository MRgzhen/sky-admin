package com.yanyu.sky.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.generator.bean.po.Student;

/**
 * 功能:  业务接口
 * @author yanyu
 * @date 2021-01-08
 */
public interface IStudentService extends IService<Student> {

    IPage<Student> listPage(Integer pageNum, Integer pageSize, Student vo);
}