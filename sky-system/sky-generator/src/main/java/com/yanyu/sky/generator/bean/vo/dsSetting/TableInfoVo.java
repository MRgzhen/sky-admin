package com.yanyu.sky.generator.bean.vo.dsSetting;

import lombok.Data;

/**
 * 表信息
 * @author yanyu
 * @date 2020/12/1
 */
@Data
public class TableInfoVo {

    /** 表名 */
    private String tableName;

    /** 引擎 */
    private String engine;

    /** 表描述 */
    private String tableComment;

}
