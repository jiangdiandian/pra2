package com.lagou.service.impl;

import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utiles.Md5Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findUserByPageAndCondition(UserVo userVo) {
        List<User> list = userMapper.findUserByPageAndCondition(userVo);
        return list;
    }

    @Override
    public User login(User user) {
        User user1 = userMapper.login(user);
        boolean res = Md5Utiles.verify(user.getPassword(), "lagou", user1.getPassword());
        if (user1 !=null&& res){
            return user1;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findAllRoleByUserId(Integer id) {
        return userMapper.findAllRoleByUserId(id);
    }

    @Override
    public void addUserRole(UserVo userVo) {
        userMapper.deleteUserRoleByUiD(userVo.getUserId());
        Integer[] list = userVo.getRoleIdList();
        for (Integer integer : list) {
            User_Role_relation us = new User_Role_relation();
            Date date = new Date();
            us.setCreatedBy("System");
            us.setUpdatedby("System");
            us.setCreatedTime(date);
            us.setUpdatedTime(date);
            us.setRoleId(integer);
            us.setUserId(userVo.getUserId());
            userMapper.addUserRole(us);
        }
    }

    @Override
    public Map<String, Object> getPermissions(Integer id) {
        //查询角色Id
        List<Role> list = userMapper.findAllRoleByUserId(id);
        ArrayList<Integer> integers = new ArrayList<>();
        for (Role role : list) {
            integers.add(role.getId());
        }
        List<Menu> parent = userMapper.findParentMenuByRoleId(integers);
        for (Menu menu : parent) {
            List<Menu> sub = userMapper.findSubMenuByParentId(menu.getId());
            menu.setSubMenuList(sub);
        }
        List<Resource> res = userMapper.findResourceByRoleId(integers);
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parent);
        map.put("resourceList",res);
        return map;
    }
}
