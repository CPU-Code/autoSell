package com.cpucode.viewmodel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/18 17:36
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class RegionViewModel implements Serializable {
    /**
     * 区域id
     */
    private Long regionId;

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 区域下点位数
     */
    private Integer nodeCount;
}
