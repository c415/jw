package com.jw.mapper;

import com.jw.pojo.PagingVO;
import com.jw.pojo.StudentCustom;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/16.
 */
public interface StudentMapperCustom {
    //分页查询学生信息
    List<StudentCustom> findByPaging(PagingVO pagingVO) throws Exception;
    //查询学生信息，和其选课信息
    StudentCustom findStudentAndSelectCourseListById(Integer id) throws Exception;
}
