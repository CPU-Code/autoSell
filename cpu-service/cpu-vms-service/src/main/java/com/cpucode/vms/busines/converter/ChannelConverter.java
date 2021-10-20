package com.cpucode.vms.busines.converter;

import com.cpucode.contract.Channel;
import com.cpucode.vms.entity.ChannelEntity;


/**
 * channel实体转换
 *
 * @author : cpucode
 * @date : 2021/10/19 15:36
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class ChannelConverter {

    /**
     * 将实体转为业务对象
     * @param channelEntity
     * @return
     */
    public static Channel convert(ChannelEntity channelEntity){
        Channel channel = new Channel();
        channel.setCapacity(channelEntity.getMaxCapacity());
        channel.setChannelId(channelEntity.getChannelCode());
        channel.setSkuId(channelEntity.getSkuId());

        return channel;
    }
}
