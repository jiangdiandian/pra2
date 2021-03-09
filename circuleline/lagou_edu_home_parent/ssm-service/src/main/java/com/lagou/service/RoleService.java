package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    public List<Role> findRoleByCondition(Role role);
    public List<Integer> findMenuIdByRoleId(Integer id);
    public void RoleContextMenu(RoleMenuVo roleMenuVo);
    public void deleteRole(Integer id);
}
