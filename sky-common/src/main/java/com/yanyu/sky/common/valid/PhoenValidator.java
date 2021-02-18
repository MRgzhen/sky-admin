package com.yanyu.sky.common.valid;

import com.yanyu.sky.common.constant.SkyAppConstant;
import com.yanyu.sky.common.constant.SkyRegexConstant;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机自定义校验器
 * @author yanyu
 */
public class PhoenValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(SkyRegexConstant.PHONE);
        if(StringUtils.isNotBlank(phone)) {
            return pattern.matcher(phone).matches();
        }
        return true;
    }
}
