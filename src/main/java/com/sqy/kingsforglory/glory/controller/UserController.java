package com.sqy.kingsforglory.glory.controller;


import com.sqy.kingsforglory.glory.pojo.RespData;
import com.sqy.kingsforglory.glory.pojo.entity.User;
import com.sqy.kingsforglory.glory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController extends HoshiController {
    @Autowired
    private UserService userService;


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(@RequestBody User user) {
        return $(respData -> {
            userService.save(user);
            String id = user.getId();
            if (id == null && id.equals("")) {
                respData.success(false).data(id).msg("添加失败");

            } else {
                respData.success(true).data(id).msg("添加成功");

            }
        });
    }

    /**
     * 根据ID删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public RespData<Boolean> del(@PathVariable String id) {
        return $(booleanRespData -> {
            if (id == null) {
                booleanRespData.success(false).msg("未发现该ID");
            } else {
                userService.removeById(id);
                booleanRespData.success(true).data(true).msg("已删除");
            }
        });
    }

    /**
     * 根据id更改密码
     *
     * @param user
     * @return
     */
    @PutMapping("/updatePwd")
    public RespData<Boolean> updatePwd(@RequestBody User user) {
        return $(booleanRespData -> {
            if (user.getId() == null && user.getId().equals(" ")) {
                booleanRespData.success(false).msg("未找到更新ID");

            } else {
                //根据ID查询的用户
                User byID = userService.getById(user.getId());
                //加密
                String Pwd = user.getPassword();
                Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                byID.setPassword(Pwd);
                userService.updateById(byID);
                booleanRespData.success(true).data(true).msg("更新成功");
            }
        });
    }


    /***
     * 根据关键字(姓名)进行查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @author：SQY
     * @Date:2020.4.8
     */
    @GetMapping("/listBySearch/{pageIndex}/{pageSize}")
    public RespData<List<User>> listBySearch(@RequestParam(name = "search", required = false, defaultValue = "") String key,
                                             @PathVariable int pageIndex, @PathVariable int pageSize) {

        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<User> userList = userService.listBySearch(key);
            listRespData.success(true).data(userList);
        });
    }

}
