package com.sqy.kingsforglory.glory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.kingsforglory.glory.pojo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listBySearch(String userId);
    /***
     * 根据账号查询用户信息
     */
    List<User> getByAccount(String account);
}
