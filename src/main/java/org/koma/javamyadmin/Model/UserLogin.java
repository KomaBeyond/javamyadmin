package org.koma.javamyadmin.Model;

import lombok.Data;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Data
public class UserLogin {
    private String host;
    private String username;
    private String password;
}
