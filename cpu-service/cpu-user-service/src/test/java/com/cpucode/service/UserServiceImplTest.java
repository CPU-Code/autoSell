package com.cpucode.service;

import com.cpucode.common.VMSystem;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;
import com.cpucode.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Duration;

/**
 * @author : cpucode
 * @date : 2021/10/19 14:30
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    /**
     * 测试登录
     * @throws IOException
     */
    @Test
    public void login() throws IOException {
        redisTemplate.opsForValue().set("13900000000", "11111", Duration.ofMinutes(5));
        LoginReq loginReq = new LoginReq();
        loginReq.setLoginType(VMSystem.LOGIN_EMP);
        loginReq.setMobile("13900000000");
        loginReq.setCode("11111");

        LoginResp resp = userService.login(loginReq);

        System.out.println(resp);
    }

    /**
     * 查看密码生成
     */
    @Test
    public void generatePwd(){
        String pwd = BCrypt.hashpw("admin", BCrypt.gensalt());

        System.out.println(pwd);
    }
}
