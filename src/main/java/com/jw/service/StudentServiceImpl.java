package com.jw.service;

import com.jw.mapper.StudentMapper;
import com.jw.mapper.StudentMapperCustom;
import com.jw.pojo.PagingVO;
import com.jw.pojo.Student;
import com.jw.pojo.StudentCustom;
import com.jw.pojo.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updataById(Integer id, StudentCustom studentCustom) throws Exception {

    }

    public void removeById(Integer id) throws Exception {

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
        return null;
    }

    public List<StudentCustom> findByName(String name) throws Exception {
        return null;
    }

    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {
        return null;
    }
}
