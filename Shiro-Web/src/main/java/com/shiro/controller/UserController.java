package com.shiro.controller;

import com.shiro.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Crete by Marlon
 * Create Date: 2018/6/28
 * Class Describe
 **/

@Controller
public class UserController {

    @RequestMapping(value = "/subLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user) {

        Subject subject = SecurityUtils.getSubject();

        System.out.println("userName:" + user.getUsername());
        System.out.println("password:" + user.getPassword());

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        if (subject.hasRole("admin")) {
            return "有Admin权限";
        }

        return "登陆成功";
    }

}
