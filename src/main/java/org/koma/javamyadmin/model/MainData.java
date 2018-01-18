package org.koma.javamyadmin.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Data
public class MainData {
    private String name = "koma";
    private int age = 23;
    private Date time = new Date();
    private String test;
    private List<TestData> dataList;
}
