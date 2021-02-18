package com.yanyu.sky.generator.bean.constant;

/**
 * @author yanyu
 */
public class GeneratorConstant {

    /** 模板文件名，通过JavaBeanName值替换形成最终的名字 */
    public static final String TEMPLATE_NAME_VARIABLE = "${NAME}";

    /** 模板文件路径，通过JavaBeanName值替换形成最终的路径 */
    public static final String TEMPLATE_PATH_VARIABLE = "${PATH}";

    /** 属性默认类型 */
    public static final String ATTR_DEFAULT = "DEFAULT";
}
