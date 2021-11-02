package com.cpucode.vms.http.controller;

import com.cpucode.viewmodel.Pager;
import com.cpucode.vms.entity.ChannelEntity;
import com.cpucode.vms.http.viewModel.SetChannelSkuReq;
import com.cpucode.vms.http.viewModel.VMChannelConfig;
import com.cpucode.vms.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 货道
 * @author : cpucode
 * @date : 2021/11/2 14:04
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    /**
     * 分页查询
     * @param pageIndex 页码
     * @param pageSize 页大小
     * @param searchMap 条件
     * @return 分页结果
     */
    @GetMapping("/page/{pageIndex}/{pageSize}")
    public Pager<ChannelEntity> findPage(@PathVariable long pageIndex,
                                         @PathVariable long pageSize,
                                         @RequestParam Map searchMap){
        return channelService.findPage(pageIndex, pageSize, searchMap);
    }

    /**
     * 获取货道
     * @param innerCode 设备编号
     * @return
     */
    @GetMapping("/channelList/{innerCode}")
    public List<ChannelEntity> getChannelList(@PathVariable("innerCode") String innerCode){
        return channelService.getChannelsByInnerCode(innerCode);
    }

    /**
     * 获取货道信息
     * @param innerCode 设备编号
     * @param channelCode 货道编号
     * @return
     */
    @GetMapping("/channelInfo/{innerCode}/{channelCode}")
    public ChannelEntity getChannelInfo(@PathVariable("innerCode") String innerCode,
                                        @PathVariable("channelCode") String channelCode){
        return channelService.getChannelInfo(innerCode, channelCode);
    }

    /**
     * 货道关联商品
     * @param req
     * @return
     */
    @PutMapping("/setSku")
    public boolean setSku(@RequestBody SetChannelSkuReq req){
        // TODO 获取售货机的策略
        Integer policyId = 0;

        return channelService.mapSku(req.getInnerCode(), req.getChannelCode(),Long.valueOf(req.getSkuId()), policyId);
    }

    /**
     * 设置货道
     * @param channelConfig 售货机货道配置
     * @return
     */
    @PutMapping("/channelConfig")
    public Boolean setChannel(@RequestBody VMChannelConfig channelConfig){
        return channelService.mapSku(channelConfig);
    }

}
