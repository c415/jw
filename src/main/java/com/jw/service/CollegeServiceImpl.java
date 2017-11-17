package com.jw.service;

import com.jw.mapper.CollegeMapper;
import com.jw.pojo.College;
import com.jw.pojo.CollegeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bojian pc on 2017/11/17.
 */
@Service
public class CollegeServiceImpl implements CollegeService{

    @Autowired
    private CollegeMapper collegeMapper;

    public List<College> findAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        CollegeExample.Criteria criteria = collegeExample.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.selectByExample(collegeExample);
    }
}
