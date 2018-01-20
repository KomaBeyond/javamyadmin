package org.koma.javamyadmin.controller;

import org.koma.javamyadmin.common.JavaMyAdminResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Controller
public class JavaMyAdminController {
    public ResponseEntity<JavaMyAdminResponse> create(Object object) {
        JavaMyAdminResponse response = new JavaMyAdminResponse();
        response.setEntity(object);
        return new ResponseEntity<JavaMyAdminResponse>(response, HttpStatus.OK);
    }
}
