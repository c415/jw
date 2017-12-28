package com.jw.mapper;

import com.jw.pojo.PagingVO;
import com.jw.pojo.TeacherCustom;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/30.
 */
public interface TeacherMapperCustom {

    //分页查询教师信息
    List<TeacherCustom> findByPaging(PagingVO pagingVO) throws Exception;
}
