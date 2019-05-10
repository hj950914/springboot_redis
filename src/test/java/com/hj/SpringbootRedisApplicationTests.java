package com.hj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    //操作redis模板
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //操作jackson模板
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() {

    }

    /**
     * 使用StringRedisTemplate
     * redis数据类型:String、List、Set、ZSet、hash、
     */
    @Test
    public void testString() {
        //操作String
        //ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        //操作List
        //ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();
        //操作set
        //SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();
        //操作ZSet
        //操作hash

        /**
         * 操作string
         */
        /*stringRedisTemplate.opsForValue().set("username", "admin");
        System.out.println(stringRedisTemplate.opsForValue().get("username"));*/

        /**
         * 操作list
         */
        /*stringRedisTemplate.opsForList().leftPush("names", "tom");
        stringRedisTemplate.opsForList().leftPushAll("names", "a", "b", "c");
        System.out.println(stringRedisTemplate.opsForList().range("names", 0, -1));*/


        /**
         * 存储对象
         */
        User user = new User();
        user.setId(110L);
        user.setUsername("hj");
        user.setPassword("123456");
        try {
            //将对象装换为json字符串
            String jsonStr = objectMapper.writeValueAsString(user);
            System.out.println(jsonStr);
            stringRedisTemplate.opsForValue().set("user", jsonStr);
            String userJson = stringRedisTemplate.opsForValue().get("user");
            //将json字符串转换成对象
            User user1 = objectMapper.readValue(userJson, User.class);
            System.out.println(user1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
