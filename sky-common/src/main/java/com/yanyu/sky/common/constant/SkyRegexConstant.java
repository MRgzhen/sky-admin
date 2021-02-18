package com.yanyu.sky.common.constant;

/**
 * 常用正则表达式
 * @author yanyu
 */
public class SkyRegexConstant {

    /** 用户名校验，4到16位（字母，数字，下划线，减号） */
    public static final String USERNAME = "^[a-zA-Z0-9_-]{4,16}$";

    /** 密码强度校验，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符 */
    public static final String PASSWROD = "^\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&*? ])\\S*$";

    /** 手机号验证 手机号(mobile phone)中国(宽松), 只要是13,14,15,16,17,18,19开头即可 */
    public static final String PHONE="^(?:(?:\\+|00)86)?1[3-9]\\d{9}$";

    /** 数字字母 */
    public static final String ALPHANUMERIC="^[A-Za-z0-9]+$";

    /** 数字字母下划线*/
    public static final String ALPHANUMERIC_UNDERLINE="^[A-Za-z0-9_]+$";

    /** 小数 */
    public static final String DECIMAL = "^\\d+\\.\\d+$";

    /** 数字 */
    public static final String NUMBER = "^\\d{1,}$";

    /** http或https */
    public static final String URL = "^http[s]?:\\/\\/.*";

    /** 身份证号, 支持1/2代(15位/18位数字) */
    public static final String ID_CARD = "(^\\d{8}(0\\d|10|11|12)([0-2]\\d|30|31)\\d{3}$)|(^\\d{6}(18|19|20)\\d{2}(0[1-9]|10|11|12)([0-2]\\d|30|31)\\d{3}(\\d|X|x)$)";

    /** 中文汉字 */
    public static final String CHINESE = "^(?:[\\u3400-\\u4DB5\\u4E00-\\u9FEA\\uFA0E\\uFA0F\\uFA11\\uFA13\\uFA14\\uFA1F\\uFA21\\uFA23\\uFA24\\uFA27-\\uFA29]|[\\uD840-\\uD868\\uD86A-\\uD86C\\uD86F-\\uD872\\uD874-\\uD879][\\uDC00-\\uDFFF]|\\uD869[\\uDC00-\\uDED6\\uDF00-\\uDFFF]|\\uD86D[\\uDC00-\\uDF34\\uDF40-\\uDFFF]|\\uD86E[\\uDC00-\\uDC1D\\uDC20-\\uDFFF]|\\uD873[\\uDC00-\\uDEA1\\uDEB0-\\uDFFF]|\\uD87A[\\uDC00-\\uDFE0])+$";
}
