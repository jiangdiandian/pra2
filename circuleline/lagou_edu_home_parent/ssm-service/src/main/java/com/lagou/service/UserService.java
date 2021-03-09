package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findUserByPageAndCondition(UserVo userVo);
    public User login(User user);
    public List<Role> findAllRoleByUserId(Integer id);
    public void addUserRole(UserVo userVo);
    public Map<String,Object> getPermissions(Integer id);
}
