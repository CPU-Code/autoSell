package com.cpucode.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.dao.UserDao;
import com.cpucode.entity.UserEntity;
import com.cpucode.service.UserService;
import com.cpucode.viewmodel.Pager;
import com.google.common.base.Strings;
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

    /**
     * 分页查询 用户信息
     * @param pageIndex 当前页
     * @param pageSize 页数
     * @param userName 用户名
     * @param roleId 角色id
     * @return
     */
    @Override
    public Pager<UserEntity> findPage(long pageIndex, long pageSize,
                                      String userName, Integer roleId){
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserEntity> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        if (Strings.isNullOrEmpty(userName)){
            wrapper.like(UserEntity::getUserName, userName);
        }

        if (roleId != null && roleId > 0){
            wrapper.eq(UserEntity::getRoleId, 1);
        }

        // TODO 不懂
        wrapper.ne(UserEntity::getRoleId, 1);

        this.page(page, wrapper);
        page.getRecords().forEach(u -> {
            u.setPassword("");
            u.setSecret("");
        });

        return Pager.build(page);
    }



}
