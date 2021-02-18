package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.IsDefaultType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典项
 * @author yanyu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dic_item")
@ApiModel(value="DicItem对象", description="数据字典项")
public class DicItem extends BaseEntity {

      private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键")
      private String id;

      @ApiModelProperty(value = "字典编码")
      private String code;

      @ApiModelProperty(value = "字典项值")
      private String value;

      @ApiModelProperty(value = "字典项名称")
      private String name;

      @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
      private EnabledType enabled;

      @ApiModelProperty(value = "默认项（数据子项可以设置默认项，有且只有一个）, 1：默认；0：不是默认")
      private IsDefaultType isDefault;

      @ApiModelProperty(value = "部门顺序， 由小到大")
      private Integer sort;

      @ApiModelProperty(value = "数据字典描述")
      private String remark;


}
