package com.yanyu.sky.generator.bean.vo.code;

import com.yanyu.sky.generator.bean.enums.NamingStrategy;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 代码生成工具类
 * @author yanyu
 * @date 2020/12/1
 */
@Data
public class GeneratorCodeSearchVo {

    /** 数据库连接主键 */
    @ApiModelProperty(value = "数据库连接名字")
    @NotEmpty(message = "{NotEmpty}")
    private String dsId;

    /** 需要生成代码的表 */
    @ApiModelProperty(value = "需要生成代码的表")
    @NotNull(message = "{NotNull}")
    private Set<String> tableNames;

    /** 模板组 */
    @ApiModelProperty(value = "模板组")
    @NotEmpty(message = "{NotEmpty}")
    private String templateGroupId;

    /** java 包名 */
    @ApiModelProperty(value = "包名")
    @NotEmpty(message = "{NotEmpty}")
    private String javaPackageName;

    /** java 模块名 */
    @ApiModelProperty(value = "模块名")
    @NotEmpty(message = "{NotEmpty}")
    private String javModuleName;

    /** 作者 */
    @ApiModelProperty(value = "作者")
    @NotEmpty(message = "{NotEmpty}")
    private String author;

    /** 表命名规则 */
    @ApiModelProperty(value = "表命名规则")
    private NamingStrategy tableNaming = NamingStrategy.UNDERLINE_TO_CAMEL;

    /** 列命名规则 */
    @ApiModelProperty(value = "列命名规则")
    private NamingStrategy columnNaming = NamingStrategy.UNDERLINE_TO_CAMEL;

    /**  忽略的表前缀 */
    @ApiModelProperty(value = "忽略的表前缀")
    private String ingoreTablePrefix;
}
