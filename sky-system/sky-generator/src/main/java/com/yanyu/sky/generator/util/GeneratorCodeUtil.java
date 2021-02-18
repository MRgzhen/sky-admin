package com.yanyu.sky.generator.util;

import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.spring.AppContext;
import com.github.mrgzhen.core.util.JSONUtil;
import com.github.mrgzhen.core.util.StringUtil;
import com.yanyu.sky.generator.bean.constant.GeneratorConstant;
import com.yanyu.sky.generator.bean.enums.NamingStrategy;
import com.yanyu.sky.generator.bean.po.Attr;
import com.yanyu.sky.generator.bean.po.Template;
import com.yanyu.sky.generator.bean.vo.code.GeneratorCodeSearchVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.ColumnInfoVo;
import com.yanyu.sky.generator.bean.vo.dsSetting.TableInfoVo;
import com.yanyu.sky.generator.bean.vo.attr.TemplateAttr;
import com.yanyu.sky.generator.dao.TemplateDescMapper;
import com.yanyu.sky.generator.dao.TemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成工具类
 * @author yanyu
 * @date 2020/12/1
 */
@Slf4j
public class GeneratorCodeUtil {

    private static TemplateMapper templateMapper = AppContext.getBean(TemplateMapper.class);
    private static TemplateDescMapper TemplateDescMapper = AppContext.getBean(TemplateDescMapper.class);
    private static final Properties properties = new Properties();

    static {
        try {
            ClassPathResource resource = new ClassPathResource("generator.properties");
            properties.load(resource.getInputStream());
        } catch (IOException e) {
            log.error("载入generator.properties资源失败", e);
        }
    }

    /**
     * 生成模板信息
     * @param templates 模板信息
     * @param attrValues 属性
     * @param tableInfoVo 表
     * @param columnInfoVos 表列
     * @param zip 文件压缩
     */
    public static void generator(List<Template> templates, GeneratorCodeSearchVo vo, TableInfoVo tableInfoVo,
                                 List<ColumnInfoVo> columnInfoVos,
                                 List<Attr> customAttrs,
                                 ZipOutputStream zip) {

        // 1. 设置变量
        TemplateAttr templateAttr = new TemplateAttr();
        templateAttr.setJavaPackageName(vo.getJavaPackageName());
        templateAttr.setJavaModuleName(vo.getJavModuleName());
        String javaBeanName = tableInfoVo.getTableName();
        if(StringUtils.isNotBlank(vo.getIngoreTablePrefix())) {
            javaBeanName = StringUtil.removePrefix(javaBeanName, vo.getIngoreTablePrefix());
        }
        if(NamingStrategy.UNDERLINE_TO_CAMEL.equals(vo.getTableNaming())) {
            javaBeanName = StringUtil.lineToCamel(javaBeanName);
        }
        templateAttr.setJavaBeanName(StringUtil.capitalFirst(javaBeanName));
        templateAttr.setLowJavaBeanName(StringUtils.isNotBlank(javaBeanName) ? javaBeanName.substring(0, 1).toLowerCase() + javaBeanName.substring(1) : "");
        templateAttr.setAuthor(vo.getAuthor());
        templateAttr.setDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        templateAttr.setTableDesc(StringUtil.defaultIfBlack(tableInfoVo.getTableComment()));
        templateAttr.setTableName(tableInfoVo.getTableName());
        templateAttr.setTableColumns(columnInfoVos.stream().map(columnInfoVo -> {
            String columnName = StringUtil.defaultIfBlack(columnInfoVo.getColumnName());
            return templateAttr.build(
                    StringUtil.defaultIfBlack(properties.getProperty(columnInfoVo.getDataType())),
                    NamingStrategy.UNDERLINE_TO_CAMEL.equals(vo.getColumnNaming()) ? StringUtil.lineToCamel(columnName) : columnName,
                    StringUtil.defaultIfBlack(columnInfoVo.getColumnComment()),
                    columnInfoVo.getColumnName()
            );
        }).collect(Collectors.toList()));

        //  2. Velocity引擎设置参数
        Map param = JSONUtil.instant().convertValue(templateAttr, Map.class);
        if(CollectionUtils.isNotEmpty(customAttrs)) {
            for(Attr customAttr : customAttrs) {
                Map<String,String> value = new HashMap<String,String>();
                value.put("name",customAttr.getName());
                value.put("value",customAttr.getValue());
                value.put("desc",customAttr.getRemark());
                param.putIfAbsent(customAttr.getName(), value);
            }
        }
        VelocityContext ctx = new VelocityContext(param);

        // 3. 处理模板
        for (Template template : templates) {
            //渲染模板
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "ds");
            ve.setProperty("ds.resource.loader.instance", new DbResourceLoader(TemplateDescMapper));
            org.apache.velocity.Template tpl = ve.getTemplate(template.getId(),"UTF-8");
            StringWriter sw = new StringWriter();
            tpl.merge(ctx, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(templateAttr, template)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new ServiceException("渲染模板失败，表名：" + templateAttr.getTableName()+",{}", e);
            }
        }
    }

    private static String getFileName(TemplateAttr templateAttr, Template template) {
        StringBuilder fileName = new StringBuilder(StringUtils.join("main", File.separator, "java",
                File.separator, StringUtils.replace(templateAttr.getJavaPackageName(),".", File.separator),
                File.separator, StringUtils.replace(templateAttr.getJavaModuleName(),".", File.separator), File.separator));

        if(StringUtils.isNotBlank(template.getModuleName())) {
            String fullModuleName = StringUtils.replace(template.getModuleName(), GeneratorConstant.TEMPLATE_PATH_VARIABLE, templateAttr.getJavaBeanName() );
            fileName.append(StringUtils.replace(fullModuleName,".", File.separator)).append(File.separator);
        }

        String fullJavaBeanName = StringUtils.replace(template.getName(), GeneratorConstant.TEMPLATE_NAME_VARIABLE, templateAttr.getJavaBeanName() );
        fileName.append(fullJavaBeanName);
        return fileName.toString();
    }
}
