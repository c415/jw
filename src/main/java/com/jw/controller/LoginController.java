package com.jw.controller;

import com.jw.pojo.Userlogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bojian pc on 2017/11/7.
 */
@Controller
public class LoginController {

    //登录验证跳转
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(Userlogin userlogin) throws Exception{
        //Shiro实现登录验证
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(), userlogin.getPassword());
        Subject subject = SecurityUtils.getSubject();
        //登录失败直接抛出异常
        subject.login(token);

        if(subject.hasRole("admin")){
            return "redirect:/admin/showStudent";
        }else if (subject.hasRole("teacher")){
            return "redirect:/teacher/showCourse";
        }else if (subject.hasRole("student")){
            return "redirect:/student/showCourse";
        }
        return "login";
    }
}
