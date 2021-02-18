package com.yanyu.sky.generator.config.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @author yanyu
 * @date 2021/1/7
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface YanyuEncryption {
}
