package com.lagou.service;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {
    public List<Menu> findMenuByPid(Integer pid);
    public List<Menu> findAllMenuNoSub();
    public Menu findMenuById(Integer id);
}
