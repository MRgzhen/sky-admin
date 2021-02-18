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
public enum SocialSexType implements IEnum<String> {
    MALE("1", "男"),
    FEMALE("2", "女"),
    UNKNOWN("3", "未知");
    @JsonValue
    private String value;
    private String name;

    @Override
    public String getValue() {
        return this.value;
    }
}