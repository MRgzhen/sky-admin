package com.yanyu.sky.sys.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统类型(sys_user表及sys_role表中type字段)
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum  UserType implements IEnum<String> {
    COMM("0","普通用户");
    @JsonValue
    private String type;
    private String name;

    @Override
    public String getValue() {
        return type;
    }
}
