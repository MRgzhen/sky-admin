package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.sys.bean.enums.DicType;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典
 * @author yanyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dic")
@ApiModel(value = "Dic对象", description = "数据字典")
public class Dic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "字典编码")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String label;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    private EnabledType enabled;

    @ApiModelProperty(value = "数据字典类型；0：预定义；1：自定义")
    private DicType type;

    @ApiModelProperty(value = "部门顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "数据字典描述")
    private String remark;


}
