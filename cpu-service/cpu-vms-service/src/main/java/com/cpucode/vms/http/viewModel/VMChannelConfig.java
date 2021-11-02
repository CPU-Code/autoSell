package com.cpucode.vms.http.viewModel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 售货机货道配置
 * @author : cpucode
 * @date : 2021/11/2 17:03
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class VMChannelConfig implements Serializable {
    /**
     * 设备编号
     */
    private String innerCode;

    /**
     * 货道信息
     */
    private List<SetChannelSkuReq> channelList;
}
