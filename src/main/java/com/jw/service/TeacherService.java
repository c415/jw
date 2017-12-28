package com.jw.service;

import com.jw.pojo.TeacherCustom;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/24.
 */
public interface TeacherService {

    //获取全部老师
    List<TeacherCustom> findAll() throws Exception;

    //获取老师人数
    int getCount() throws Exception;

    //分页查询老师信息
    List<TeacherCustom> findByPaging(Integer toPage) throws Exception;

    //根据名字查询教师信息
    List<TeacherCustom> findByName(String name) throws Exception;

    Boolean save(TeacherCustom teacherCustom) throws Exception;

    TeacherCustom findById(Integer id) throws Exception;

    void updateById(TeacherCustom teacherCustom) throws Exception;

    //根据id删除老师信息
    void removeById(Integer id) throws Exception;
}
