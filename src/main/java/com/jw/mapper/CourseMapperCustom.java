package com.jw.mapper;

import com.jw.pojo.CourseCustom;
import com.jw.pojo.PagingVO;

import java.util.List;

<<<<<<< HEAD

=======
/**
 * Created by Jacey on 2017/6/29.
 */
>>>>>>> ada6fb89bbe015b967d3f1d46298d89248f43e0a
public interface CourseMapperCustom {

    //分页查询学生信息
    List<CourseCustom> findByPaging(PagingVO pagingVO) throws Exception;

}
