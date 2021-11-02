package com.cpucode.vms.http.viewModel;

import lombok.Data;

/**
 *
 * @author : cpucode
 * @date : 2021/11/2 16:53
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class SetChannelSkuReq {
    /**
     * 设备编号
     */
    private String innerCode;

    /**
     * 货道编号
     */
    private String channelCode;

    /**
     * 商品Id
     */
    private String skuId;
}
