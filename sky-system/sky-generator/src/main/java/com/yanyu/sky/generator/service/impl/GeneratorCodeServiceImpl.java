package com.yanyu.sky.generator.service.impl;

import cn.hutool.core.io.IoUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yanyu.sky.generator.bean.enums.AttrType;
import com.yanyu.sky.generator.bean.po.Attr;
import com.yanyu.sky.generator.bean.po.DsSetting;
import com.yanyu.sky.generator.bean.po.Template;
import com.yanyu.sky.generator.bean.vo.code.GeneratorCodeSearchVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.ColumnInfoVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.TableInfoVo;
import com.yanyu.sky.generator.dao.AttrMapper;
import com.yanyu.sky.generator.dao.DsSettingMapper;
import com.yanyu.sky.generator.dao.TemplateDescMapper;
import com.yanyu.sky.generator.dao.TemplateMapper;
import com.yanyu.sky.generator.service.IGeneratorCodeService;
import com.yanyu.sky.generator.util.DyncDataSourceUtil;
import com.yanyu.sky.generator.util.GeneratorCodeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author yanyu
 * @date 2020/12/1
 */
@Service
@AllArgsConstructor
public class GeneratorCodeServiceImpl implements IGeneratorCodeService {

    private DsSettingMapper dsSettingMapper;
    private TemplateMapper templateMapper;
    private TemplateDescMapper templateDescMapper;
    private AttrMapper attrMapper;
    private DyncDataSourceUtil dsUtils;

    @Override
    public byte[] generatorCode(GeneratorCodeSearchVo vo) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 1. 获取数据连接信息
        DsSetting dsSetting = dsSettingMapper.selectById(vo.getDsId());

        // 2. 判断是否存在数据库，不存在则 新增数据连接
        dsUtils.dsCreateIfAbsent(dsSetting);

        // 3. 获取分组下的模板
        List<Template> templates = templateMapper.selectList(Wrappers.<Template>lambdaQuery().eq(Template::getTemplateGroupId, vo.getTemplateGroupId()));

        // 4. 获取分组下自定义属性
        List<Attr> customAttrs = attrMapper.selectList(Wrappers.<Attr>lambdaQuery().eq(Attr::getTemplateGroupId, vo.getTemplateGroupId())
                                .eq(Attr::getType, AttrType.CUSTOM));

        // 5. 代码生成逻辑
        Set<String> tables = vo.getTableNames();
        tables.stream().forEach(tableName -> {
            DynamicDataSourceContextHolder.push(dsSetting.getName());
            // 5.1 获取表信息
            TableInfoVo tableInfoVo = dsSettingMapper.getTableByName(tableName);
            // 5.2 获取表列信息
            List<ColumnInfoVo> columnInfoVos = dsSettingMapper.listColumn(tableName);
            DynamicDataSourceContextHolder.poll();
            // 5.3 代码生成
            GeneratorCodeUtil.generator(templates, vo, tableInfoVo, columnInfoVos, customAttrs, zip);
        });

        // 5. 关闭流
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }
}
