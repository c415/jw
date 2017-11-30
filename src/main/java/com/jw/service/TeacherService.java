package com.jw.service;

import com.jw.pojo.TeacherCustom;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/24.
 */
public interface TeacherService {

    //获取全部老师
    List<TeacherCustom> findAll() throws Exception;
}
