package com.jw.service.impl;

import com.jw.exception.CustomException;
import com.jw.mapper.CollegeMapper;
import com.jw.mapper.CourseMapper;
import com.jw.mapper.TeacherMapper;
import com.jw.mapper.TeacherMapperCustom;
import com.jw.pojo.*;
import com.jw.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bojian pc on 2017/11/24.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherMapperCustom teacherMapperCustom;
    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseMapper courseMapper;


    public List<TeacherCustom> findAll() throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUsernameIsNotNull();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        List<TeacherCustom> teacherCustomList = null;
        if(teacherList != null){
            teacherCustomList = new ArrayList<TeacherCustom>();
            for(Teacher t : teacherList){
                System.out.println(t);
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                teacherCustomList.add(teacherCustom);
            }
        }

        return teacherCustomList;
    }

    public int getCount() throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUseridIsNotNull();
        return teacherMapper.countByExample(teacherExample);
    }

    public List<TeacherCustom> findByPaging(Integer toPage) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPage);

        List<TeacherCustom> list = teacherMapperCustom.findByPaging(pagingVO);
        return list;
    }

    public List<TeacherCustom> findByName(String name) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUsernameLike("%"+name+"%");
        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        List<TeacherCustom> teacherCustomList = null;
        if(list != null){
            teacherCustomList = new ArrayList<TeacherCustom>();
            for(Teacher t : list){
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                College college = collegeMapper.selectByPrimaryKey(t.getCollegeid());
                teacherCustom.setCollegeName(college.getCollegename());
                teacherCustomList.add(teacherCustom);
            }
        }
        return teacherCustomList;
    }

    public Boolean save(TeacherCustom teacherCustom) throws Exception {
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCustom.getUserid());
        if(teacher == null){
            teacherMapper.insert(teacherCustom);
            return true;
        }
        return false;
    }

    public TeacherCustom findById(Integer id) throws Exception {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        TeacherCustom teacherCustom = null;
        if(teacher != null){
            teacherCustom = new TeacherCustom();
            BeanUtils.copyProperties(teacher, teacherCustom);
        }
        return teacherCustom;
    }

    public void updateById(TeacherCustom teacherCustom) throws Exception {
        teacherMapper.updateByPrimaryKey(teacherCustom);
    }

    public void removeById(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacheridEqualTo(id);
        List<Course> list = courseMapper.selectByExample(courseExample);

        if (list.size() != 0) {
            throw new CustomException("请先删除该名老师所教授的课程");
        }

        teacherMapper.deleteByPrimaryKey(id);
    }
}
