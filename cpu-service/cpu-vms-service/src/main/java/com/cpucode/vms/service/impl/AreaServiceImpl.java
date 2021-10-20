package com.cpucode.vms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.vms.dao.AreaDao;
import com.cpucode.vms.entity.AreaEntity;
import com.cpucode.vms.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:38
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {

    /**
     * 获取所有根节点
     * @return
     */
    @Override
    public List<AreaEntity> getAllRootAreaList() {
        return this.getAllChildren(0);
    }

    /**
     * 获取所有一级子节点
     * @param parentId 父节点
     * @return
     */
    @Override
    public List<AreaEntity> getAllChildren(int parentId) {
        QueryWrapper<AreaEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(AreaEntity::getParentId, parentId);

        return this.list(qw);
    }
}
