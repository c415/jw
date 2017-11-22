package com.jw.controller;


/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:58 2017/11/21
 */



import com.jw.pojo.CourseCustom;
import com.jw.pojo.SelectedCourseCustom;
import com.jw.service.CourseService;
import com.jw.service.SelectedCourseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    // 显示我的课程
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {
        logger.info("Enter stuCourseShow"+model);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);

        logger.info("Exit stuCourseShow"+list);
        return "teacher/showCourse";
    }



    // 显示成绩
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {

        logger.info("Enter gradeCourse"+model);
        if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        model.addAttribute("selectedCourseList", list);

        logger.info("Exit gradeCourse"+model);
        return "teacher/showGrade";
    }




}
