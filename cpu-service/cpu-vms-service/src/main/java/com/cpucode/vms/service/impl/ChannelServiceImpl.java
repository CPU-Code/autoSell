package com.cpucode.vms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.exception.LogicException;
import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.dao.ChannelDao;
import com.cpucode.vms.entity.ChannelEntity;
import com.cpucode.vms.entity.SkuEntity;
import com.cpucode.vms.http.viewModel.VMChannelConfig;
import com.cpucode.vms.service.ChannelService;
import com.cpucode.vms.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/11/2 16:41
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelDao, ChannelEntity> implements ChannelService {

    @Autowired
    private SkuService skuService;

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<ChannelEntity> findList(Map searchMap) {
        QueryWrapper queryWrapper = createQueryWrapper(searchMap);

        return this.list(queryWrapper);
    }

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @param searchMap
     * @return
     */
    @Override
    public Pager<ChannelEntity> findPage(long pageIndex, long pageSize, Map searchMap) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ChannelEntity> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);
        QueryWrapper queryWrapper = createQueryWrapper(searchMap);
        this.page(page, queryWrapper);

        Pager<ChannelEntity> pageResult = new Pager<>();
        pageResult.setCurrentPageRecords(page.getRecords());
        pageResult.setPageIndex(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalCount(page.getTotal());

        return pageResult;
    }

    /**
     * 获取货道
     * @param innerCode 设备编号
     * @return
     */
    @Override
    public List<ChannelEntity> getChannelsByInnerCode(String innerCode) {
        LambdaQueryWrapper<ChannelEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChannelEntity::getInnerCode, innerCode);

        return this.list(queryWrapper);
    }

    /**
     * 获取货道信息
     * @param innerCode 设备编号
     * @param channelCode 货道编号
     * @return
     */
    @Override
    public ChannelEntity getChannelInfo(String innerCode, String channelCode) {
        LambdaQueryWrapper<ChannelEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChannelEntity::getInnerCode, innerCode)
                .eq(ChannelEntity::getChannelCode, channelCode);

        return this.getOne(queryWrapper);
    }

    /**
     * 补货
     * @param channel
     * @return
     */
    @Override
    public boolean supply(ChannelEntity channel) {
        channel.setLastSupplyTime(LocalDateTime.now());

        return this.save(channel);
    }

    /**
     * 关联商品
     * @param innerCode 设备编号
     * @param channelCode 货道编号
     * @param skuId 商品Id
     * @param policyId 策略id
     * @return
     */
    @Override
    public boolean mapSku(String innerCode, String channelCode, long skuId, Integer policyId) {
        ChannelEntity channel = this.getChannelInfo(innerCode, channelCode);
        if (channel == null){
            throw new LogicException("该货道不存在");
        }

        channel.setSkuId(skuId);
        if(policyId >= 0){
            int realPrice = this.calRealPrice(skuId, policyId);
            channel.setPrice(realPrice);
        } else {
            int realPrice = skuService.getById(skuId).getPrice();
            channel.setPrice(realPrice);
        }

        return this.updateById(channel);
    }

    /**
     * 获取售货机中商品的真实售价
     * @param innerCode 设备编号
     * @param skuId 商品id
     * @return
     */
    @Override
    public Integer getRealPrice(String innerCode, Long skuId) {
        LambdaQueryWrapper<ChannelEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChannelEntity::getInnerCode, innerCode)
                .eq(ChannelEntity::getSkuId, skuId);
        List<ChannelEntity> channelList = this.list(queryWrapper);
        if (channelList == null || channelList.size() <= 0){
            return 0;
        }

        // 获取第一个 价格
        return channelList.stream().findFirst().get().getPrice();
    }

    /**
     * TODO 设置货道
     * @param channelConfig 售货机货道配置
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean mapSku(VMChannelConfig channelConfig) {
        //
       //  VmPolicyEntity vmPolicyEntity = policyService.getPolicyByInnerCode(channelConfig.getInnerCode());

//        Integer policyId = 0;
//        if(vmPolicyEntity != null){
//            policyId = vmPolicyEntity
//        }


        return true;
    }

    /**
     * 条件构建
     * @param searchMap 查询条件
     * @return
     */
    private QueryWrapper createQueryWrapper(Map searchMap){
        QueryWrapper queryWrapper = new QueryWrapper();
        if (searchMap != null){
            queryWrapper.allEq(searchMap);
        }

        return queryWrapper;
    }

    /**
     * TODO 计算真实售价
     * @param skuId
     * @return
     */
    private Integer calRealPrice(Long skuId, Integer policyId){
        // PolicyEntity policy = policyService.getById(policyId);
        SkuEntity skuEntity = null;

        return skuEntity.getPrice();
    }
}
