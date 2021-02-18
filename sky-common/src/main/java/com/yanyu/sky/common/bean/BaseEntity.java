package com.yanyu.sky.common.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanyu
 */
@Data
@ToString
public class BaseEntity extends Model implements Serializable {

    /** 创建用户 */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新用户 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    /** 是否删除，0：未删除，1：删除 */
    private Integer isDel;
}
