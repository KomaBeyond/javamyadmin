package org.koma.javamyadmin.model;

import lombok.Data;
import org.koma.javamyadmin.enums.ResourceType;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Data
public class LoginData {
    private ResourceType type = ResourceType.MYSQL;
    private String host;
    private String username;
    private String password;
}
