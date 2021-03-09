package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findRoleByCondition(Role role) {
        return roleMapper.findRoleByCondition(role.getName());
    }

    @Override
    public List<Integer> findMenuIdByRoleId(Integer id) {
        return roleMapper.findMenuIdByRoleId(id);
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        roleMapper.deleteMenuByRoleIdInMid(roleMenuVo.getRoleId());
        for (Integer integer : roleMenuVo.getMenuIdList()) {
            Role_menu_relation rm = new Role_menu_relation();
            rm.setRoleId(roleMenuVo.getRoleId());
            rm.setMenuId(integer);
            Date date = new Date();
            rm.setCreatedBy("System");
            rm.setUpdatedby("System");
            rm.setUpdatedTime(date);
            rm.setCreatedTime(date);
            roleMapper.addMenuInMid(rm);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteMenuByRoleIdInMid(id);
        roleMapper.deleteRole(id);
    }
}
