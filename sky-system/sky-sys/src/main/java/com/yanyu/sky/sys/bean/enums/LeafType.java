package com.yanyu.sky.sys.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否是叶子节点
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum LeafType implements IEnum<String> {
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

