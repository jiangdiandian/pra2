package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleServicer;
    @RequestMapping("/findAllRole")
    public ResponseResult findRoleByCondition(@RequestBody Role role){
        System.out.println(role.getName());
        List<Role> list = roleServicer.findRoleByCondition(role);
        ResponseResult res = new ResponseResult(true, 200, "查询成功", list);
        return res;
    }
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findMenuByPid(-1);
        ResponseResult res = new ResponseResult(true, 200, "查询成功", list);
        return res;
    }
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> list = roleServicer.findMenuIdByRoleId(roleId);
        ResponseResult res = new ResponseResult(true, 200, "查询成功", list);
        return res;
    }
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleServicer.RoleContextMenu(roleMenuVo);
        ResponseResult res = new ResponseResult(true, 200, "修改成功",null);
        return res;
    }
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleServicer.deleteRole(id);
        ResponseResult res = new ResponseResult(true, 200, "删除成功",null);
        return res;
    }
}
