package org.koma.javamyadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Controller
@RequestMapping({"/", "/javamyadmin"})
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
