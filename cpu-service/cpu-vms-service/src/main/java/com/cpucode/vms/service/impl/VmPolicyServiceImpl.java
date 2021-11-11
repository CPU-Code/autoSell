package com.cpucode.vms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.vms.dao.VmPolicyDao;
import com.cpucode.vms.entity.VmPolicyEntity;
import com.cpucode.vms.service.VmPolicyService;
import org.springframework.stereotype.Service;

/**
 * 机器和策略关联
 *
 * @author : cpucode
 * @date : 2021/11/11 14:09
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class VmPolicyServiceImpl extends ServiceImpl<VmPolicyDao, VmPolicyEntity> implements VmPolicyService {
}
