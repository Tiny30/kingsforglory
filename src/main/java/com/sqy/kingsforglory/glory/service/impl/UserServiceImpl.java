package com.sqy.kingsforglory.glory.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.kingsforglory.glory.dao.UserDao;
import com.sqy.kingsforglory.glory.pojo.entity.User;
import com.sqy.kingsforglory.glory.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends HoshiService<UserDao, User> implements UserService {
    /**
     * 根据关键字查询所有信息
     * @param key
     * @return lsit
     */
    @Override
    public List<User> listBySearch(String key) {
        key = "%"+key+"%" ;
        List<User> userList;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", key)
                .or().like("account", key);
        if (needPage()) {
            userList = page(getPage(), wrapper).getRecords();
        } else {
            userList = list(wrapper);
        }
        return userList;
    }

    @Override
    public List<User> getByAccount(String account) {
        List<User> list;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("account",account);
        list = list(queryWrapper);
        return list;
    }


}
