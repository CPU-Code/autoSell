package com.cpucode.http.controller;

import com.cpucode.entity.UserEntity;
import com.cpucode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该controller不向web前端暴露，只给内部调用，会在 做拦截
 *
 * @author : cpucode
 * @date : 2021/10/19 14:09
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/auth")
@CacheConfig(cacheNames = "auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return
     */
    @Cacheable(cacheNames = "user", key = "targetClass + methodName + #p0")
    @GetMapping("/user/{userId}")
    public UserEntity getUser(@PathVariable("userId") int userId){
        return userService.getById(userId);
    }

}
