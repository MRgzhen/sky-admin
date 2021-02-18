package com.yanyu.sky.generator.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能:  实体类
 * @author yanyu
 * @date 2021-01-08
 */
@Data
@TableName("student")
@ApiModel(value = "Student对象}", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "用户名称")
    private String username;
    @ApiModelProperty(value = "生日")
    private Date age;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
    @ApiModelProperty(value = "备注")
    private String remarks;
    @ApiModelProperty(value = "删除标识")
    private String delFlag;
    }