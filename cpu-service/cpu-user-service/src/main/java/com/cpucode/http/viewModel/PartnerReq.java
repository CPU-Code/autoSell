package com.cpucode.http.viewModel;

import lombok.Data;

/**
 * @author : cpucode
 * @date : 2021/10/18 16:46
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class PartnerReq {

    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 合作商名称
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 分成比例
     */
    private Integer ratio;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 所在省
     */
    private String province;

    /**
     * 详细地址
     */
    private String addr;
}
