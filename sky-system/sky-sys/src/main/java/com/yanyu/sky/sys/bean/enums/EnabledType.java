package com.yanyu.sky.sys.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启停状态（所有有enabled字段的表）
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum EnabledType implements IEnum<String> {
    ENABLED("1","启用"),
    DISABLED("0","禁用");
    @JsonValue
    private String value;
    private String name;

    @Override
    public String getValue() {
        return this.value;
    }
}