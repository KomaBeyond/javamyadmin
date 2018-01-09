package org.koma.javamyadmin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.koma.javamyadmin.Model.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author koma <komazhang@foxmail.com>
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("shiroLoginFailure") String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    /**
     * 这里通过 RedirectAttributes 来设置 redirect 之后的属性
     * 在 redirect 的控制器上通过 @ModelAttribute 来接收对应的信息
     * 这里接收值通过 Model 去获取也是同样的原理
     * 这样该值在 redirect 之后就被框架及时清除, 防止数据重复和省去人工清除的繁琐操作
     */
    @RequestMapping(value = "/do-login", method = RequestMethod.POST)
    public String doLogin(UserLogin user, RedirectAttributes attributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
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
        } catch (Exception e) {
            attributes.addFlashAttribute("shiroLoginFailure", "服务器错误");
        }
        return view;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
