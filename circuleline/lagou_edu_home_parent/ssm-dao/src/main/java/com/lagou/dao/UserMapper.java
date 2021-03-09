package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    public List<User> findUserByPageAndCondition(UserVo userVo);
    public User login(User user);
    public void deleteUserRoleByUiD(Integer uid);
    public void addUserRole(User_Role_relation user_role_relation);

    public List<Role> findAllRoleByUserId(Integer id);
    public List<Menu> findParentMenuByRoleId(List<Integer> rids);
    public List<Menu> findSubMenuByParentId(Integer pid);
    public List<Resource> findResourceByRoleId(List<Integer> rid);
}
