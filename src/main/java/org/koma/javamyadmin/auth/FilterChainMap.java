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
        //指定针对所有 test 控制器都允许匿名访问
        mapps.put("/test/**", "anon");
        return mapps;
    }
}
