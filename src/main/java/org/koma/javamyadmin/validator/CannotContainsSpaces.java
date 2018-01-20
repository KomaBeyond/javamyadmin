package org.koma.javamyadmin.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义数据校验器,判断字符串中不能包括 1 个以上的空格
 *
 * @author koma <komazhang@foxmail.com>
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CannotContainsSpacesValidator.class})
public @interface CannotContainsSpaces {
    String message() default "字符串中包含超过两个空格";

    //////必须的属性
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
