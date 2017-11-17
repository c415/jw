package com.jw.controller;

import com.jw.pojo.PagingVO;
import com.jw.pojo.StudentCustom;
import com.jw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/7.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StudentService studentService;

    //学生信息显示
    @RequestMapping(value = "/showStudent", method = RequestMethod.GET)
    public String showStudent(Model model, Integer page )throws Exception{

        PagingVO pagingVO = new PagingVO();
        List<StudentCustom> list = studentService.findByPaging(page, pagingVO);
        model.addAttribute("studentList", list);
        model.addAttribute("pagingVO",pagingVO);
        return "admin/showStudent";
    }
}
