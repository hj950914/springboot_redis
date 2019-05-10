package com.hj.controller;

import com.hj.entity.User;
import com.hj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Author: hj
 * Date: 2019-05-10 14:23
 * Description: <描述>
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param user
     */
    @PostMapping(value = "/users", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void addUser(@RequestBody User user) {
        //添加用户进入缓存
        userService.addUser(user);
    }

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    /**
     * 更新用户
     *
     * @param user
     * @param id
     */
    @PutMapping(value = "/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping("/users/{id}")
    public void delUser(@PathVariable Long id) {
        userService.delUser(id);
    }
}
