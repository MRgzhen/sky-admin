package com.yanyu.sky.sys.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据字典默认值（sys_dic is_default字段）
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum IsDefaultType implements IEnum<String> {
    YES("1","是"),
    NO("0","否");
    @JsonValue
    private String value;
    private String name;

    @Override
    public String getValue() {
        return this.value;
    }
}
