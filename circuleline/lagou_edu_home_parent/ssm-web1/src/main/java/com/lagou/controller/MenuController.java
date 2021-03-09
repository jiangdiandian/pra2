package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findAllMenuNoSub();
        ResponseResult res = new ResponseResult(true, 200, "查询成功", list);
        return res;
    }
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        if (id==-1){
            //添加中的查询
            List<Menu> list = menuService.findMenuByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInFo",null);
            map.put("parentMenuList",list);
            ResponseResult res = new ResponseResult(true, 200, "查询成功", map);
            return res;
        }else {
            //修改中的查询
            Menu menu = menuService.findMenuById(id);
            List<Menu> list = menuService.findMenuByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInFo", menu);
            map.put("parentMenuList", list);
            ResponseResult res = new ResponseResult(true, 200, "查询成功", map);
            return res;
        }
    }
}
