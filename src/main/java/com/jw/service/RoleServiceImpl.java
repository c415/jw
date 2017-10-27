package com.jw.service;

import com.jw.mapper.RoleMapper;
import com.jw.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role findByid(Integer id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }
}
