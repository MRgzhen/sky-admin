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
public enum NamingStrategy implements IEnum<String> {

    /**
     * 不做任何改变，原样输出
     */
    NO_CHANGE("1"),
    /**
     * 下划线转驼峰命名
     */
    UNDERLINE_TO_CAMEL("2");

    @JsonValue
    private String value;

    @Override
    public String getValue() {
        return this.value;
    }

}
