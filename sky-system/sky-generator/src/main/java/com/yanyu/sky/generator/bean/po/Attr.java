package com.yanyu.sky.generator.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.generator.bean.enums.AttrType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 模板属性
 * @author yanyu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gen_attr")
@ApiModel(value = "Attr对象", description = "模板属性")
public class Attr implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "分组id")
    private String templateGroupId;

    @ApiModelProperty(value = "属性名")
    private String name;

    @ApiModelProperty(value = "属性值")
    private String value;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "类型：1：自定义，2：默认")
    private AttrType type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新用户")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除，0：未删除，1：删除")
    private String isDel;


}
