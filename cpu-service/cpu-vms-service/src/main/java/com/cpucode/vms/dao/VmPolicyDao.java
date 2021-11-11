package com.cpucode.vms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpucode.vms.entity.VmPolicyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机器和策略关联
 *
 * @author : cpucode
 * @date : 2021/11/11 14:08
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Mapper
public interface VmPolicyDao extends BaseMapper<VmPolicyEntity> {
}
