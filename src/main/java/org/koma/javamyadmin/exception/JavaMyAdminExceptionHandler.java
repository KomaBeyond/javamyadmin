package org.koma.javamyadmin.exception;

import org.koma.javamyadmin.common.JavaMyAdminResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 通过注解 @ControllerAdvice 实现控制器通知功能
 * 这样可以把带有注解 @ExceptionHandler 的异常处理方法收集到一起
 * 通过对这些方法归类,可以形成整个应用的异常处理模块
 *
 * @author koma <komazhang@foxmail.com>
 */
@ControllerAdvice
public class JavaMyAdminExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<JavaMyAdminResponse> handleNotFoundException(NotFoundException e) {
        JavaMyAdminResponse response = new JavaMyAdminResponse();
        response.setStatusDescription(e.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return generate(response);
    }

    private ResponseEntity<JavaMyAdminResponse> generate(JavaMyAdminResponse response) {
        return new ResponseEntity<JavaMyAdminResponse>(response, HttpStatus.OK);
    }
}
