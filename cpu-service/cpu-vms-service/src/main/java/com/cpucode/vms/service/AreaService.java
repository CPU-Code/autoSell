package com.cpucode.vms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.vms.entity.AreaEntity;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:37
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface AreaService extends IService<AreaEntity> {
    /**
     * 获取所有根节点
     * @return
     */
    List<AreaEntity> getAllRootAreaList();

    /**
     * 获取所有一级子节点
     * @param parentId
     * @return
     */
    List<AreaEntity> getAllChildren(int parentId);
}
