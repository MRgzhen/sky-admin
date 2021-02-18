package com.yanyu.sky.sys.bean.enums;


import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;

/**
 * 缓存key
 * @author yanyu
 */
@AllArgsConstructor
public enum DicType implements IEnum<String> {

    SYSTEM("1","自定义"),
    CUSTOM("0","预定义");
    @JsonValue
    private String value;
    private String name;

    @Override
    public String getValue() {
        return this.value;
    }
}
