package com.jw.service.impl;

import com.jw.mapper.TeacherMapper;
import com.jw.pojo.Teacher;
import com.jw.pojo.TeacherCustom;
import com.jw.pojo.TeacherExample;
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
}
