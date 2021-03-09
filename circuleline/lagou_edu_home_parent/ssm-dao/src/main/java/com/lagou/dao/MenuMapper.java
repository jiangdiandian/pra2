package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {
    public List<Menu> findAllMenu(Integer parentId);
    public List<Menu> findAllMenuNoSub();
    public Menu findMenuById(Integer id);
}
