package com.yanyu.sky.generator.bean.vo.dsSetting;

import lombok.Data;

/**
 * 表字段信息
 *
 * @author yanyu
 * @date 2020/12/1
 */
@Data
public class ColumnInfoVo {

    /** 列名 */
    private String columnName;

    /** 数据类型 */
    private String dataType;

    /** 列描述 */
    private String columnComment;

    /** 主键标识 */
    private String columnKey;
}
