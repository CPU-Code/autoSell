package com.cpucode.vms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.exception.LogicException;
import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.dao.SkuDao;
import com.cpucode.vms.entity.ChannelEntity;
import com.cpucode.vms.entity.SkuClassEntity;
import com.cpucode.vms.entity.SkuEntity;
import com.cpucode.vms.service.ChannelService;
import com.cpucode.vms.service.SkuClassService;
import com.cpucode.vms.service.SkuService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/20 11:27
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuDao, SkuEntity> implements SkuService {

    @Autowired
    private SkuClassService skuClassService;
    @Autowired
    private ChannelService channelService;

    /**
     * 修改
     * @param skuEntity
     * @return
     * @throws LogicException
     */
    @Override
    public boolean update(SkuEntity skuEntity) throws LogicException {
        UpdateWrapper<SkuEntity> uw = new UpdateWrapper<>();
        uw.lambda()
                .set(SkuEntity::getClassId, skuEntity.getClassId())
                .set(SkuEntity::getSkuName, skuEntity.getSkuName())
                .set(SkuEntity::getUnit, skuEntity.getUnit())
                .set(SkuEntity::getSkuImage, skuEntity.getSkuImage())
                .set(SkuEntity::getPrice, skuEntity.getPrice())
                .eq(SkuEntity::getSkuId, skuEntity.getSkuId());

        return this.update(uw);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        QueryWrapper<ChannelEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(ChannelEntity::getSkuId, id);

        if (channelService)
    }

    /**
     * 分页查询
     * @param pageIndex 当前页
     * @param pageSize 页码
     * @param skuName 商品名称
     * @return
     */
    @Override
    public Pager<SkuEntity> findPage(long pageIndex, long pageSize, Integer classId, String skuName) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SkuEntity> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);

        LambdaQueryWrapper<SkuEntity> qw = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(skuName)){
            qw.like(SkuEntity::getSkuName, skuName);
        }

        if(classId != null && classId > 0){
            qw.eq(SkuEntity::getClassId, classId);
        }

        this.page(page, qw);

        return Pager.build(page);
    }

    /**
     * 获取所有商品类别
     * @return
     */
    @Override
    public List<SkuClassEntity> getAllClass() {
        return skuClassService.list();
    }
}
