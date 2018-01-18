package org.koma.javamyadmin.exception;

import org.springframework.http.HttpStatus;

/**
 * @author koma <komazhang@foxmail.com>
 */
public class JavaMyAdminException extends RuntimeException {
    private String errorDescription;
    private HttpStatus status;

    public JavaMyAdminException(String errorDescription, HttpStatus status) {
        this.errorDescription = errorDescription;
        this.status = status;
    }
}
