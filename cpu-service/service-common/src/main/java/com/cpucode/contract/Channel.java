package com.cpucode.contract;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:46
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class Channel implements Serializable {
    /**
     * 商品Id
     */
    private long skuId;

    /**
     * 余量
     */
    private int capacity;

    /**
     * 货道编号
     */
    private String channelId;
}
