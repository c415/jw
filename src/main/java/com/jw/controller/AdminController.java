package com.jw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bojian pc on 2017/11/7.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/showStudent", method = RequestMethod.GET)
    public String showStudent(){
        return "showStudent";
    }
}
