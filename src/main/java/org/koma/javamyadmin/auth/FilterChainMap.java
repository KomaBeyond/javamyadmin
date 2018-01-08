package org.koma.javamyadmin.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * @author koma <komazhang@foxmail.com>
 */
public class FilterChainMap {
    public static Map<String, String> mapps() {
        Map<String, String> mapps = new HashMap<String, String>();
        mapps.put("/main/**", "authc");
        return mapps;
    }
}
