package com.yanyu.sky.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanyu.sky.generator.bean.po.TemplateDesc;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据库配置 Mapper 接口
 * @author yanyu
 * @date 2020/11/18
 */
public interface TemplateDescMapper extends BaseMapper<TemplateDesc> {

    @Select("select * from gen_template_desc where template_id = #{id}")
    TemplateDesc getById(String id);

    @Select("select td.content from gen_template_desc td\n" +
            "join gen_template t on td.template_id = t.id\n" +
            "where template_group_id = #{groupId}")
    List<TemplateDesc> listDescByGroupId(String groupId);

}
