package com.cpucode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpucode.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:34
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
}
