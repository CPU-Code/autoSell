package com.cpucode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.dao.UserDao;
import com.cpucode.entity.UserEntity;
import com.cpucode.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : cpucode
 * @date : 2021/10/15 22:15
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    /**
     * 获取所有运营人员数量
     * @return 人员数量
     */
    @Override
    public Integer getOperatorCount(){
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        // TODO 修改查询值
        wrapper.eq(UserEntity::getRoleCode, "1002");

        return this.count(wrapper);
    }

    /**
     * 获取所有维修员数量
     * @return
     */
    @Override
    public Integer getRepairerCount(){
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        // TODO 修改查询值
        wrapper.eq(UserEntity::getRoleCode, "1003");

        return this.count(wrapper);
    }





}
