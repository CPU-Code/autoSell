package com.cpucode.feignService.fallback;

import com.cpucode.feignService.TaskService;
import com.cpucode.viewmodel.UserWork;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : cpucode
 * @date : 2021/10/19 13:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Component
@Slf4j
public class TaskServiceFallbackFactory implements FallbackFactory<TaskService> {
    @Override
    public TaskService create(Throwable throwable) {
        log.error("调用工单服务失败", throwable);

        return new TaskService() {
            @Override
            public Integer getSupplyAlertValue() {
                return 0;
            }

            @Override
            public UserWork getUserWork(Integer userId, LocalDateTime start, LocalDateTime end) {
                UserWork userWork = new UserWork();
                userWork.setCancelCount(0);
                userWork.setProgressTotal(0);
                userWork.setWorkCount(0);
                userWork.setUserId(userId);

                return userWork;
            }
        };
    }
}
