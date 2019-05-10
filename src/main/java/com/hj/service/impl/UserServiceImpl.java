package com.hj.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj.entity.User;
import com.hj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Author: hj
 * Date: 2019-05-10 14:24
 * Description: <描述>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate srt;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addUser(User user) {
        try {
            String userJson = objectMapper.writeValueAsString(user);
            srt.opsForValue().set(user.getId().toString(), userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(Long id) {
        //从缓存中取出用户信息
        String userStr = srt.opsForValue().get(id.toString());
        try {
            //将取出的字符串信息转换为对象
            if (userStr != null) {
                return objectMapper.readValue(userStr, User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user, Long id) {
        try {
            //先转换json格式
            String value = objectMapper.writeValueAsString(user);
            //如果存在要更新的用户,才进行更新
            if (srt.hasKey(id.toString())) {
                //存入缓存l
                srt.opsForValue().set(id.toString(), value);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delUser(Long id) {
        srt.delete(id.toString());
    }
}
