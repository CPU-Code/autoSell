package com.cpucode.vms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.exception.LogicException;
import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.dao.PolicyDao;
import com.cpucode.vms.entity.PolicyEntity;
import com.cpucode.vms.entity.VmPolicyEntity;
import com.cpucode.vms.service.PolicyService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/11/2 19:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
@Slf4j
public class PolicyServiceImpl extends ServiceImpl<PolicyDao, PolicyEntity> implements PolicyService {

    /**
     * TODO 获取售货机的策略
     * @param innerCode 售货机编号
     * @return
     */
    @Override
    public VmPolicyEntity getPolicyByInnerCode(String innerCode) {
        QueryWrapper<VmPolicyEntity> qw = new QueryWrapper<>();

        return null;
    }

    /**
     * TODO 给售货机应用策略
     * @param innerCode 售货机编号
     * @param policyId 策略id
     * @return
     */
    @Override
    public boolean applyPolicy(List<String> innerCode, int policyId) {
        return false;
    }


    /**
     * TODO 取消策略
     * @param innerCode 售货机编号
     * @param policyId 策略id
     * @return
     */
    @Override
    public boolean cancelPolicy(String innerCode, int policyId) {
        return false;
    }


    /**
     * 搜索
     * @param policyName 策略名称
     * @param pageIndex 页数
     * @param pageSize 页码
     * @return
     */
    @Override
    public Pager<PolicyEntity> search(String policyName, long pageIndex, long pageSize) {
        LambdaQueryWrapper<PolicyEntity> qw = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(policyName)){
            qw.like(PolicyEntity::getPolicyName, policyName);
        }

        Page<PolicyEntity> page = new Page<>(pageIndex, pageSize);

        this.page(page, qw);

        return Pager.build(page);
    }

    /**
     * 删除策略
     * @param policyId 策略id
     * @return
     */
    @Override
    public Boolean delete(Integer policyId) {
        LambdaQueryWrapper qw = new LambdaQueryWrapper<VmPolicyEntity>();

        // TODO 查询是否被使用


        int count = 0;

        if(count > 0){
            throw new LogicException("该策略正在使用");
        }

        return this.removeById(policyId);
    }
}
