package com.cpucode.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:06
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@FeignClient(value = "user-service",fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService {
    /**
     * 获取用户信息
     * @param userId 用户id
     * @return
     */
    @GetMapping("/auth/user/{userId}")
    String getUser(@PathVariable("userId") int userId);
}
