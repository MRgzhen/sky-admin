package com.yanyu.sky.generator.bean.po;

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
@TableName("gen_template")
@ApiModel(value="Template对象", description="模板信息")
public class Template extends BaseEntity {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键")
      private String id;

      @ApiModelProperty(value = "分组id")
      private String templateGroupId;

      @ApiModelProperty(value = "模板名")
      private String name;

      @ApiModelProperty(value = "模块包名")
      private String moduleName;

      @ApiModelProperty(value = "模板描述")
      private String remark;

      @ApiModelProperty(value = "部门id")
      private String dsDept;


}
