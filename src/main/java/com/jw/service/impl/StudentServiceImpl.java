package com.jw.service.impl;

import com.jw.mapper.CollegeMapper;
import com.jw.mapper.StudentMapper;
import com.jw.mapper.StudentMapperCustom;
import com.jw.mapper.UserloginMapper;
import com.jw.pojo.*;

import com.jw.pojo.PagingVO;
import com.jw.pojo.Student;
import com.jw.pojo.StudentCustom;
import com.jw.pojo.StudentExample;

import com.jw.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bojian pc on 2017/11/16.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapperCustom studentMapperCustom;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;



    public void updateById(StudentCustom studentCustom) throws Exception {
        studentMapper.updateByPrimaryKey(studentCustom);
    }

    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }

    public List<StudentCustom> findByPaging(Integer toPageNo, PagingVO pagingVO) throws Exception {
        List<StudentCustom> list = null;
        pagingVO.setTotalCount(getCountStudent());
        if(toPageNo == null || toPageNo == 0){
            pagingVO.setToPageNo(1);
            list = studentMapperCustom.findByPaging(pagingVO);
        } else {
            pagingVO.setToPageNo(toPageNo);
            list = studentMapperCustom.findByPaging(pagingVO);
        }
        return list;
    }

    //保存学生信息
    public Boolean save(StudentCustom studentCustoms) throws Exception {
        Student student = studentMapper.selectByPrimaryKey(studentCustoms.getUserid());
        if(student == null){
            studentMapper.insert(studentCustoms);
            return true;
        }
        return false;
    }

    //返回学生数量
    public int getCountStudent() throws Exception {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUsernameIsNotNull();

        return studentMapper.countByExample(studentExample);
    }

    public StudentCustom findById(Integer id) throws Exception {
        Student student  = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }
        return studentCustom;
    }

    public List<StudentCustom> findByName(String name) throws Exception {
        StudentExample studentExample = new StudentExample();
        //自定义查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Student> list = studentMapper.selectByExample(studentExample);

        List<StudentCustom> studentCustomList = null;

        if (list != null) {
            studentCustomList = new ArrayList<StudentCustom>();
            for (Student s : list) {
                StudentCustom studentCustom = new StudentCustom();
                //类拷贝
                BeanUtils.copyProperties(s, studentCustom);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                studentCustom.setcollegeName(college.getCollegename());

                studentCustomList.add(studentCustom);
            }

        }
        return studentCustomList;
    }

    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {
        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setOver(true);
            }
        }
        return studentCustom;
    }
}
