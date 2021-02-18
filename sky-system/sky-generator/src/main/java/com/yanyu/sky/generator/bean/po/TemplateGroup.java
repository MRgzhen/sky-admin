package com.yanyu.sky.generator.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 模板分组
 * @author yanyu
 * @date 2020/12/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gen_template_group")
@ApiModel(value="TemplateGroup对象", description="模板分组")
public class TemplateGroup extends BaseEntity {

     private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键")
      @NotNull(message = "{NotNull}",groups = {UpdateGroup.class})
      @Null(message = "{Null}",groups = {AddGroup.class})
      private String id;

      @ApiModelProperty(value = "组名称")
      @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
      private String name;

      @ApiModelProperty(value = "组描述")
      private String remark;

      @ApiModelProperty(value = "排序，由小到大")
      @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
      private Integer sort;

      @ApiModelProperty(value = "部门id")
      private String dsDept;

}
