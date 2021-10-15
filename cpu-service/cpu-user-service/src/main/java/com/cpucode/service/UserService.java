package com.cpucode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.entity.UserEntity;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 获取所有运营人员数量
     * @return 人员数量
     */
    Integer getOperatorCount();

    /**
     * 获取所有维修员数量
     * @return
     */
    Integer getRepairerCount();
}
