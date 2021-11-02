package com.cpucode.vms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.entity.ChannelEntity;
import com.cpucode.vms.http.viewModel.VMChannelConfig;

import java.util.List;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/10/20 13:38
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface ChannelService extends IService<ChannelEntity> {
    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    List<ChannelEntity> findList(Map searchMap);

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @param searchMap
     * @return
     */
    Pager<ChannelEntity> findPage(long pageIndex, long pageSize, Map searchMap);

    /**
     * 获取货道
     * @param innerCode 设备编号
     * @return
     */
    List<ChannelEntity> getChannelsByInnerCode(String innerCode);

    /**
     * 获取货道信息
     * @param innerCode 设备编号
     * @param channelCode 货道编号
     * @return
     */
    ChannelEntity getChannelInfo(String innerCode, String channelCode);

    /**
     * 补货
     * @param channel
     * @return
     */
    boolean supply(ChannelEntity channel);


    /**
     * 关联商品
     * @param innerCode
     * @param channelCode
     * @param skuId
     * @return
     */
    boolean mapSku(String innerCode, String channelCode, long skuId, Integer policyId);


    /**
     * 获取售货机中商品的真实售价
     * @param innerCode
     * @param skuId
     * @return
     */
    Integer getRealPrice(String innerCode,Long skuId);


    /**
     * 设置货道
     * @param channelConfig 售货机货道配置
     * @return
     */
    Boolean mapSku(VMChannelConfig channelConfig);
}
