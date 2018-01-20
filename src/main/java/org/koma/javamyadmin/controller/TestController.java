package org.koma.javamyadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.koma.javamyadmin.common.JavaMyAdminResponse;
import org.koma.javamyadmin.model.LoginData;
import org.koma.javamyadmin.model.TestData;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController extends JavaMyAdminController {
    @RequestMapping(value = "/data-filter", method = RequestMethod.POST)
    public ResponseEntity<JavaMyAdminResponse> testDataFilter(@Valid @RequestBody TestData testData,
                                                              BindingResult bindingResult) {
        /**
         * 检测数据校验是否通过
         */
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.warn("error: {}->{}", fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        log.info("test data: {}", testData);
        Date time = testData.getTime();
        Date newTime = new Date();
        newTime.setTime(time.getTime()+1000);
        testData.setTime(newTime);
        testData.setLoginData(new LoginData());
        return create(testData);
    }
}
