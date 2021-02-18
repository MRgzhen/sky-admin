package com.yanyu.sky.generator.bean.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板信息
 * @author yanyu
 * @date 2020/12/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gen_template_desc")
@ApiModel(value = "TemplateDesc对象", description = "模板信息")
public class TemplateDesc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId
    private String templateId;

    @ApiModelProperty(value = "模板内容")
    private String content;

    @ApiModelProperty(value = "部门id")
    private String dsDept;

}
