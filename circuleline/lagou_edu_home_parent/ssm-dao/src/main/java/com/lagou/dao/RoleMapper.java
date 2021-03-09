package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    public List<Role> findRoleByCondition(@Param("name") String name);
    public List<Integer> findMenuIdByRoleId(Integer id);
    public void deleteMenuByRoleIdInMid(Integer roleId);
    public void addMenuInMid(Role_menu_relation role_menu_relation);
    public void deleteRole(Integer id);
}
