package org.koma.javamyadmin.model;

import lombok.Data;
import org.koma.javamyadmin.validator.CannotContainsSpaces;

import javax.validation.constraints.Max;
import java.util.Date;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Data
public class TestData {
    @CannotContainsSpaces
    private String name;
    @Max(100)
    private int age;
    private Date time;
    private LoginData loginData;
}
