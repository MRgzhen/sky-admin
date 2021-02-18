package com.yanyu.sky.generator.bean.vo.attr;

import com.yanyu.sky.generator.bean.po.Attr;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessTokenJackson1Deserializer;
import org.springframework.security.oauth2.common.OAuth2AccessTokenJackson1Serializer;
import org.springframework.security.oauth2.common.OAuth2AccessTokenJackson2Deserializer;
import org.springframework.security.oauth2.common.OAuth2AccessTokenJackson2Serializer;

import java.util.List;

/**
 * 代码生成参数
 * @author yanyu
 * @date 2020/12/1
 */
@Data
public class TemplateAttr {

    /** java包名 */
    private String javaPackageName;
    /** java模块名 */
    private String javaModuleName;
    /** java Bean名字 */
    private String javaBeanName;
    /** java Bean小写的名字 */
    private String lowJavaBeanName;
    /** 作者 */
    private String author;
    /** 日期 2020/01/01 */
    private String date;
    /** 表描述 */
    private String tableDesc;
    /** 表名 */
    private String tableName;
    /** javaBean 名字 */
    private List<TableColumnAttr> tableColumns;

    public TableColumnAttr build(String attrType, String attrName, String attrDesc, String columnName) {
        return new TableColumnAttr(attrType, attrName, attrDesc, columnName);
    }

    @Data
    @AllArgsConstructor
    public class TableColumnAttr {
        /** 属性类型 */
        private String attrType;
        /** 属性名 */
        private String attrName;
        /** 描述 */
        private String attrDesc;
        /** 表字段列名 */
        private String columnName;
    }
}
