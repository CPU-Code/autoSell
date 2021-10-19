package com.cpucode.viewmodel;

import lombok.Data;

/**
 * @author : cpucode
 * @date : 2021/10/19 14:22
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class PartnerViewModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 合作商名称
     */
    private String name;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 所在省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 详细地址
     */
    private String addr;

    /**
     * 分成比例
     */
    private Integer ratio;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 设备数量
     */
    private Integer vmCount;
}
