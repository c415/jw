package com.jw.mapper;

import com.jw.pojo.CourseCustom;
import com.jw.pojo.PagingVO;

import java.util.List;



public interface CourseMapperCustom {

    //分页查询学生信息
    List<CourseCustom> findByPaging(PagingVO pagingVO) throws Exception;

}
