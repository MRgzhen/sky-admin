package com.yanyu.sky.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义手机校验注解类
 * @author yanyu
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoenValidator.class)
public @interface Phone {
    String message() default "{手机格式不正确}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
