package com.jw.mapper;

import com.jw.pojo.CourseCustom;
import com.jw.pojo.PagingVO;

import java.util.List;


<<<<<<< HEAD
=======

>>>>>>> e2c0a51e44a72629f85612f3487edf74df3b9e15
public interface CourseMapperCustom {

    //分页查询学生信息
    List<CourseCustom> findByPaging(PagingVO pagingVO) throws Exception;

}
