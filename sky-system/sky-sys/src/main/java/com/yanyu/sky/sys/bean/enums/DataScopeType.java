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
public enum DataScopeType implements IEnum<String> {
    /** 本人 */
    SELF("1","本人"),
    /** 本部门 */
    DEPT("2","本部门"),
    /** 本部门及下级部门 */
    DEPT_WITH_CHILD("3","本部门及下级部门"),
    /** 全部 */
    ALL("4","全部"),
    /** 自定义数据权限 */
    CUSTOM("5","自定义数据权限");
    @JsonValue
    private String value;
    private String name;
    @Override
    public String getValue() {
        return this.value;
    }
}
