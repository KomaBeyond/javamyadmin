package org.koma.javamyadmin.model;

import lombok.Data;

import java.util.Date;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Data
public class TestData {
    private String name;
    private int age;
    private Date time;
    private LoginData loginData;
}
