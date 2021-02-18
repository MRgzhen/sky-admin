package com.yanyu.sky.generator.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanyu.sky.generator.bean.po.Student;
import com.yanyu.sky.generator.dao.StudentMapper;
import com.yanyu.sky.generator.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能:  业务实现类
 * @author yanyu
 * @date 2021-01-08
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public IPage<Student> listPage(Integer pageNum, Integer pageSize, Student vo) {
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Student>lambdaQuery(vo));
    }

}