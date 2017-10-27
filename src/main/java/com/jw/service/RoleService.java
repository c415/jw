package com.jw.service;

import com.jw.pojo.Role;

/**
 *  Role 权限表Service层
 */
public interface RoleService {

    Role findByid(Integer id) throws Exception;

}
