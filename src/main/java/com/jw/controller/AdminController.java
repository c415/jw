package com.jw.controller;

import com.jw.exception.CustomException;
import com.jw.pojo.*;
import com.jw.service.*;
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

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

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


    //显示课程信息
    @RequestMapping(value = "/showCourse", method = RequestMethod.GET)
    public String showCourse(Model model, Integer page) throws Exception{
        List<CourseCustom> list = null;
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(courseService.getCountCouse());
        if(page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        }else{
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showCourse";
    }

    //搜索课程
    @RequestMapping(value = "/selectCourse", method = RequestMethod.GET)
    public String selectCourse(String findByName, Model model) throws  Exception{
        if(findByName == null||"".equals(findByName.trim()) ){
            return "forward:/admin/showCourse";
        }else{
            List<CourseCustom> list = courseService.findByName(findByName.trim());
            model.addAttribute("courseList", list);
            model.addAttribute("value", findByName.trim());
            return "admin/showCourse";
        }

    }

    //跳转到添加课程页
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addCourse(Model model) throws Exception{
        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.findAll();
        model.addAttribute("collegeList",collegeList);
        model.addAttribute("teacherList",list);
        return "admin/addCourse";
    }

    //添加课程
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addCourse(CourseCustom courseCustom, Model model) throws Exception{
        Boolean result = courseService.save(courseCustom);
        if(!result){
            model.addAttribute("message","课程号重复");
            return "error";
        }

        return "redirect:/admin/showCourse";
    }

    //删除课程
    @RequestMapping(value = "/removeCourse", method = RequestMethod.GET)
    public String deleteCourse(Integer id, Model model) throws Exception{
        if(id == null){
            return "admin/showCourse";
        }
        if(courseService.removeById(id)){
            return "redirect:/admin/showCourse";
        }else {
            model.addAttribute("message", "学生已选该课程课，不能删除");
            return "error";
        }
    }

    //跳转到修改课程页
    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    public String editCourse(Integer id, Model model) throws Exception{

        if(id == null){
            return "redirect:/admin/showCourse";
        }
        CourseCustom course = courseService.findById(id);
        if(course == null){
            throw new CustomException("未找到该课程");
        }
        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("collegeList",collegeList);
        model.addAttribute("teacherList",list);
        model.addAttribute("course", course);

        return "admin/editCourse";
    }


    //修改课程信息
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public String editCourse(CourseCustom course) throws Exception{
        courseService.upadteById(course);
        return "redirect:/admin/showCourse";
    }


}
