package com.yanyu.sky.common.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanyu
 */
@Data
@ToString
public class DataScopeEntity extends BaseEntity{

    /** 部门作为数据权限 */
    @TableField(fill = FieldFill.INSERT)
    private String dsDept;
}
