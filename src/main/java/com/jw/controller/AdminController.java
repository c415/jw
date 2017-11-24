package com.jw.controller;

import com.jw.exception.CustomException;
import com.jw.pojo.*;
import com.jw.service.CollegeService;
import com.jw.service.StudentService;
import com.jw.service.UserloginService;
import org.jetbrains.annotations.NotNull;
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

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserloginService userloginService;

    //学生信息显示
    @RequestMapping(value = "/showStudent", method = RequestMethod.GET)
    public String showStudent(Model model, Integer page )throws Exception{

        PagingVO pagingVO = new PagingVO();
        List<StudentCustom> list = studentService.findByPaging(page, pagingVO);
        model.addAttribute("studentList", list);
        model.addAttribute("pagingVO",pagingVO);
        return "admin/showStudent";
    }

    //添加学生信息
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(Model model) throws Exception {
        List<College> list = collegeService.findAll();
        model.addAttribute("collegeList", list);
        return "admin/addStudent";
    }

    //提交添加学生信息
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(StudentCustom studentCustom, Model model) throws Exception{
        Boolean result = studentService.save(studentCustom);

        if(!result){
            model.addAttribute("message","学号重复");
            return "error";
        }

        //添加登录信息
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(studentCustom.getUserid().toString());
        userlogin.setPassword("123");
        userlogin.setRole(2);
        userloginService.save(userlogin);
        return "redirect:/admin/showStudent";
    }

    //删除学生
    @NotNull
    @RequestMapping(value = "/removeStudent", method = RequestMethod.GET)
    private String removeStudent(Integer id) throws Exception{

        studentService.removeById(id);
        return "forward:/admin/showStudent";
    }

    //搜索学生
    @RequestMapping(value = "/selectStudent", method = RequestMethod.GET)
    private String selectStudent(String findByName, Model model) throws Exception{
        if(findByName == null||"".equals(findByName.trim()) ){
            return "forward:/admin/showStudent";
        }else{
            List<StudentCustom> list = studentService.findByName(findByName.trim());
            model.addAttribute("studentList", list);
            model.addAttribute("value", findByName.trim());
            return "admin/showStudent";
        }
    }
    //跳转到修改学生信息页面
    @RequestMapping(value = "/editStudent", method = RequestMethod.GET)
    public String editStudent(Integer id, Model model) throws Exception{
        if(id == null){
            return "redirect:/admin/showStudent";
        }

        StudentCustom studentCustom = studentService.findById(id);
        if(studentCustom == null){
            throw new CustomException("未找到学生");
        }
        List<College> list = collegeService.findAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("student", studentCustom);
        return "admin/editStudent";
    }

    //修改学生信息
    @RequestMapping(value = "/editStudent", method = RequestMethod.POST)
    public String editStudent(StudentCustom studentCustom) throws Exception{
        studentService.updateById(studentCustom);

        return "redirect:/admin/showStudent";

    }

}
