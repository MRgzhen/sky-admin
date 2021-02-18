package com.yanyu.sky.sys.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型（sys_menu表中的type字段）
 * @author yanyu
 */
@Getter
@AllArgsConstructor
public enum MenuType implements IEnum<String> {
    CATALOG("0","目录"),
    MENU("1","菜单"),
    BUTTON("2","按钮"),
    OTHER("3","其他");
    @JsonValue
    private String value;
    private String name;

    @Override
    public String getValue() {
        return this.value;
    }
}
