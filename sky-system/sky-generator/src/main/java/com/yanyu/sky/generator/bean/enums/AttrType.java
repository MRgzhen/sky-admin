package com.yanyu.sky.generator.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum AttrType implements IEnum<String> {

    CUSTOM("1"),

    DEFAULT("2");

    @JsonValue
    private String value;

    @Override
    public String getValue() {
        return this.value;
    }

}
