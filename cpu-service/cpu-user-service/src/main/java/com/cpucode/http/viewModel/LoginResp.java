package com.cpucode.http.viewModel;

import lombok.Data;

/**
 * @author : cpucode
 * @date : 2021/10/17 22:04
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class LoginResp {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色编号
     */
    private String roleCode;
    /**
     * jwt令牌
     */
    private String token;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 所属区域Id
     */
    private String regionId;

    /**
     * 消息
     */
    private String msg;

    /**
     * 是否是运维人员
     */
    private Boolean isRepair;
}
