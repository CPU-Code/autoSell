package com.cpucode.viewmodel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/20 10:55
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class AreaViewModel implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 父Id
     */
    private Integer parentId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 地区编码
     */
    private String adCode;

    /**
     * 城市区号
     */
    private String cityCode;

    /**
     * 地区级别
     */
    private String areaLevel;
}
