package org.koma.javamyadmin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.koma.javamyadmin.model.LoginData;
import org.koma.javamyadmin.model.MainData;
import org.koma.javamyadmin.model.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("shiroLoginFailure") String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    /**
     * 这里通过 RedirectAttributes 来设置 redirect 之后的属性
     * 在 redirect 的控制器上通过 @ModelAttribute 来接收对应的信息
     * 这里接收值通过 model 去获取也是同样的原理
     * 这样该值在 redirect 之后就被框架及时清除, 防止数据重复和省去人工清除的繁琐操作
     */
    @RequestMapping(value = "/do-login", method = RequestMethod.POST)
    public String doLogin(LoginData user, RedirectAttributes attributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword(),
                user.getHost()
        );
        Subject subject = SecurityUtils.getSubject();
        String view = "redirect:/login";

        try {
            subject.login(token);
            view = "redirect:/main";
        } catch (IncorrectCredentialsException e) {
            attributes.addFlashAttribute("shiroLoginFailure", "用户名/密码错误");
        } catch (UnknownAccountException e) {
            attributes.addFlashAttribute("shiroLoginFailure", "用户名/密码错误");
        } catch (ExcessiveAttemptsException e) {
            attributes.addFlashAttribute("shiroLoginFailure", "尝试登录次数超过限制");
        } catch (AuthenticationException e) {
            attributes.addFlashAttribute("shiroLoginFailure", "身份认证失败");
        }
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public MainData main() {
//        String sql = "show databases";
//        jdbcTemplate.query(sql, new MyRowMapper());
        MainData mainData = new MainData();

        List<TestData> testDataList = new ArrayList<TestData>();
        TestData testData1 = new TestData();
        testData1.setName("test--1");
        testDataList.add(testData1);

        TestData testData2 = new TestData();
        testData2.setName("test--2");
        testDataList.add(testData2);

        mainData.setDataList(testDataList);
        return mainData;
    }

    class MyRowMapper implements RowMapper<String> {
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            System.out.println(resultSet.getString("Database"));
            return null;
        }
    }
}
