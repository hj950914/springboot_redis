package com.hj.service;

import com.hj.entity.User;

/**
 * Author: hj
 * Date: 2019-05-10 14:24
 * Description: 用户缓存接口
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据用户id查找用户
     *
     * @param id 用户id
     * @return
     */
    public User findUser(Long id);

    /**
     * 根据用户id更新用户信息
     *
     * @param user
     * @param id
     */
    public void updateUser(User user, Long id);

    /**
     * 根据用户id删除用户
     *
     * @param id
     */
    public void delUser(Long id);
}
