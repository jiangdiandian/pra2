package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findUserByPage(@RequestBody UserVo userVo){
        List<User> list = userService.findUserByPageAndCondition(userVo);
        System.out.println(list.get(0).getCreateTime());
        ResponseResult res = new ResponseResult(true, 200, "响应成功", list);
        return res;
    }
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request){
        User user1 = userService.login(user);
        if (user1!=null){
            String s = UUID.randomUUID().toString();
            System.out.println("token:"+s);
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",s);
            map.put("user_id",user1.getId());
            map.put("user",user1);
            ResponseResult res = new ResponseResult(true, 1, "响应成功", map);

            request.getSession().setAttribute("access_token",s);
            request.getSession().setAttribute("user_id",user1.getId());
            request.getSession().setAttribute("user",user1);
            return res;
        }else {
           return new ResponseResult(true,1,"用户名或密码错误",null);
        }
    }
    @RequestMapping("/findUserRoleById")
    public ResponseResult findAllRoleByUserId(Integer id){
        List<Role> list = userService.findAllRoleByUserId(id);
        ResponseResult res = new ResponseResult(true, 200, "分配角色回显成功", list);
        return res;
    }
    @RequestMapping("/userContextRole")
    public ResponseResult findAllRoleByUserId(@RequestBody UserVo userVo){
        userService.addUserRole(userVo);
        ResponseResult res = new ResponseResult(true, 200, "分配角色成功", null);
        return res;
    }
    @RequestMapping("/getUserPermissions")
    public ResponseResult getPermissions(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token =(String) request.getSession().getAttribute("access_token");
        if (authorization.equals(token)){
            Integer uid = (Integer)request.getSession().getAttribute("user_id");
            System.out.println(uid);
            Map<String, Object> permissions = userService.getPermissions(uid);
            ResponseResult res = new ResponseResult(true, 200, "查询成功", permissions);
            return res;
        }else {
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }
    }
}
