package org.koma.javamyadmin.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义数据校验器实现类,根据定义,该类只能处理 String 类型
 *
 * @author koma <komazhang@foxmail.com>
 */
@Slf4j
public class CannotContainsSpacesValidator implements ConstraintValidator<CannotContainsSpaces, String> {

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.warn("validation data: {}", s);
        return false;
    }
}
