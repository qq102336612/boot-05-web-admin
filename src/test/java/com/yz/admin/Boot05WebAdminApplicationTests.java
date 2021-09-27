package com.yz.admin;

import com.yz.admin.bean.User;
import com.yz.admin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from student", Long.class);
        log.info("记录总数：{}",aLong);
    }

    @Test
    void testUserMapper(){
        User user = userMapper.selectById(1);
        log.info("user信息：{}", user);

    }

    @Test
    void  testRedis(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello", "world");
        String hello = operations.get("hello");
        log.info("hello:{}", hello);
    }

}
