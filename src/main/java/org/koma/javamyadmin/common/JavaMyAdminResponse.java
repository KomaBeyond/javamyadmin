package org.koma.javamyadmin.common;

import lombok.Data;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Data
public class JavaMyAdminResponse {
    private int statusCode;             //状态码,对应常见 HTTP 状态码
    private String statusDescription;   //状态描述
    private Object entity;              //响应实体信息
}
