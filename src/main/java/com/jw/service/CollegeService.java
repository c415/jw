package com.jw.service;

import com.jw.pojo.College;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/17.
 */
public interface CollegeService {

    //查询所有学院名称
    List<College> findAll() throws Exception;
}
