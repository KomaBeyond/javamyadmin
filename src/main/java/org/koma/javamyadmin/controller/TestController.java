package org.koma.javamyadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.koma.javamyadmin.common.JavaMyAdminResponse;
import org.koma.javamyadmin.model.TestData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController extends JavaMyAdminController {
    @RequestMapping(value = "/data-filter", method = RequestMethod.POST)
    public ResponseEntity<JavaMyAdminResponse> testDataFilter(@RequestBody TestData testData) {
        log.info("test data: {}", testData);
        Date time = testData.getTime();
        Date newTime = new Date();
        newTime.setTime(time.getTime()+1000);
        testData.setTime(newTime);
        return create(testData);
    }
}
