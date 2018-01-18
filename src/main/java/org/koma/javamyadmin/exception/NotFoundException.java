package org.koma.javamyadmin.exception;

import org.springframework.http.HttpStatus;

/**
 * @author koma <komazhang@foxmail.com>
 */
public class NotFoundException extends JavaMyAdminException {
    public NotFoundException(String errorDescription) {
        super(errorDescription, HttpStatus.NOT_FOUND);
    }
}
