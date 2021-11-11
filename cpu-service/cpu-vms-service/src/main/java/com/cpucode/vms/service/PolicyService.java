package com.cpucode.vms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.entity.PolicyEntity;
import com.cpucode.vms.entity.VmPolicyEntity;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/11/2 19:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface PolicyService extends IService<PolicyEntity> {
    /**
     * 获取售货机的策略
     * @param innerCode 售货机编号
     * @return
     */
    VmPolicyEntity getPolicyByInnerCode(String innerCode);

    /**
     * 给售货机应用策略
     * @param innerCode 售货机编号
     * @param policyId 策略id
     * @return
     */
    boolean applyPolicy(List<String> innerCode, int policyId);

    /**
     * 取消策略
     * @param innerCode 售货机编号
     * @param policyId 策略id
     * @return
     */
    boolean cancelPolicy(String innerCode, int policyId);

    /**
     * 搜索
     * @param policyName 策略名称
     * @param pageIndex 页数
     * @param pageSize 页码
     * @return
     */
    Pager<PolicyEntity> search(String policyName, long pageIndex, long pageSize);

    /**
     * 删除策略
     * @param policyId 策略id
     * @return
     */
    Boolean delete(Integer policyId);
}
