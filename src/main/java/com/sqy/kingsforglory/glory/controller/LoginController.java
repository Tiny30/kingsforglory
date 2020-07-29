package com.sqy.kingsforglory.glory.controller;


import com.sqy.kingsforglory.glory.author.TokenGenerator;
import com.sqy.kingsforglory.glory.pojo.RespData;
import com.sqy.kingsforglory.glory.pojo.entity.User;
import com.sqy.kingsforglory.glory.service.UserService;
import com.sqy.kingsforglory.stream.HStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
/**
 *  功能：登入登出，返回json格式
 *  方法：loginIn(),loginOut()
 *  作者：
 *  日期：2020.3.20
 */
public class LoginController extends HoshiController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RespData<User> register(@RequestBody User user) {
        return $(resp -> {
            List<User> users = userService.getByAccount(user.getAccount());
            if (HStream.count(users) > 0) {
                resp.success(false).msg("该账号已存在");
            } else {
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                //插入用户数据
                userService.save(user);
                //生成token
                String token = TokenGenerator.generateToken(user);
                //设置token
                user.setToken(token);
                //更新token
                userService.updateById(user);
                //注册成功
                resp.success(true).data(user).msg("注册成功");
            }
        });
    }

    /**
     * 登录成功 返回token 登录凭证
     *
     * @return
     */
    @PostMapping("/login")
    public RespData<User> login(@RequestBody User user) {
        return $(resp -> {
            List<User> users = userService.getByAccount(user.getAccount());
            if (HStream.count(users) != 1) {
                resp.success(false).msg("用户名或者密码错误");
            } else {
                //从数据库查询出来的用户
                User temp = users.get(0);
                //md5加密客户端密码
                String clientPwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()) ;
                //对比加密后的密码是否一致
                boolean equals = Objects.equals(temp.getPassword(), clientPwd);
                if (equals) {
                    //生成token
                    String token = TokenGenerator.generateToken(temp);
                    //设置token
                    temp.setToken(token);
                    //保存到数据库
                    userService.updateById(temp);
                    temp.setPassword(null);
                    //返回token
                    resp.success(true).data(temp).msg("登录成功");
                } else {
                    resp.success(false).msg("用户名或者密码错误");
                }
            }
        });
    }

    @DeleteMapping("/logout/{userId}")
    public RespData<Boolean> logout(@PathVariable String userId) {
        return $(resp -> {
            User byId = userService.getById(userId);
            byId.setToken("");
            userService.updateById(byId);
            resp.success(true).data(true).msg("登出成功");
        });
    }
}
