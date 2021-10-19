package com.cpucode.feignService;

import com.cpucode.feignService.fallback.TaskServiceFallbackFactory;
import com.cpucode.viewmodel.UserWork;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

/**
 * @author : cpucode
 * @date : 2021/10/19 13:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@FeignClient(value = "task-service",fallbackFactory = TaskServiceFallbackFactory.class)
public interface TaskService {
    @GetMapping("/task/supplyAlertValue")
    Integer getSupplyAlertValue();

    /**
     * 查询用户工作量
     * @param userId 用户id
     * @param start 起始时间
     * @param end 结束时间
     * @return
     */
    @GetMapping("/userWork/{userId}/{start}/{end}")
    UserWork getUserWork(@PathVariable Integer userId,
                         @PathVariable LocalDateTime start,
                         @PathVariable LocalDateTime end);
}
