package com.jw.service;

import com.jw.pojo.CourseCustom;

import java.util.List;

/*
*Author:zhangxin_an
*Description:CourseService课程信息.
*Data:Created in 9:58 2017/11/21
 */

public interface CourseService {

    //根据教师id查找课程
    List<CourseCustom> findByTeacherID(Integer id) throws Exception;
}
