package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpucode.entiry.AbstractEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 售货机货道实体类
 *
 * @author : cpucode
 * @date : 2021/10/19 15:39
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_channel", autoResultMap = true, resultMap = "channelMap")
public class ChannelEntity extends AbstractEntity implements Serializable {
    /**
     * 货道Id
     */
    @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    /**
     * 货道编号
     */
    @TableField(value = "channel_code")
    private String channelCode;

    /**
     * 商品Id
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * 货道商品最终售价
     */
    private Integer price;

    /**
     * 售货机Id
     */
    @TableField(value = "vm_id")
    private Long vmId;

    /**
     * 售货机软编号
     */
    @TableField(value = "inner_code")
    private String innerCode;

    /**
     * 货道最大容量
     */
    @TableField(value = "max_capacity")
    private Integer maxCapacity;

    /**
     * 货道当前容量
     */
    @TableField(value = "current_capacity")
    private Integer currentCapacity;

    /**
     * 上次补货时间
     */
    @TableField(value = "last_supply_time")
    private LocalDateTime lastSupplyTime;

    @TableField(exist = false)
    private SkuEntity sku;
}
