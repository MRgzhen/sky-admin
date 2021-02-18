package com.yanyu.sky.sys.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum IsSysType implements IEnum<String> {
    COMMON("0","不是"),
    SUPER_ADMIN("1","是");
    @JsonValue
    private String value;
    private String name;

    @Override
    public String getValue() {
        return value;
    }
}
