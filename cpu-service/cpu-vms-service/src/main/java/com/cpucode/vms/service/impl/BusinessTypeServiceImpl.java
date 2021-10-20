package com.cpucode.vms.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.vms.dao.BusinessTypeDao;
import com.cpucode.vms.entity.BusinessTypeEntity;
import com.cpucode.vms.service.BusinessTypeService;
import org.springframework.stereotype.Service;

/**
 * @author : cpucode
 * @date : 2021/10/20 11:15
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class BusinessTypeServiceImpl extends ServiceImpl<BusinessTypeDao, BusinessTypeEntity> implements BusinessTypeService {
}
