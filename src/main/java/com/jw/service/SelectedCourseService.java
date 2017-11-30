package com.jw.service;



import com.jw.pojo.SelectedCourseCustom;

import java.util.List;

/**
 * 选课表servic层
 */
public interface SelectedCourseService {

    //根据课程ID查询课程
    List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception;





    //选课
    void save(SelectedCourseCustom selectedCourseCustom) throws Exception;


    //退课
    void remove(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //查询指定学生成绩
    SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception;



}
