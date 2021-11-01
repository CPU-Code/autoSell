package com.cpucode.vms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.exception.LogicException;
import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.entity.SkuClassEntity;
import com.cpucode.vms.entity.SkuEntity;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/20 11:26
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface SkuService extends IService<SkuEntity> {

    /**
     * 修改
     * @param skuEntity
     * @return
     */
    boolean update(SkuEntity skuEntity) throws LogicException;

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @param skuName
     * @return
     */
    Pager<SkuEntity> findPage(long pageIndex, long pageSize, Integer classId, String skuName);

    /**
     * 获取所有商品类别
     * @return
     */
    List<SkuClassEntity> getAllClass();
}
