package com.cpucode.http.viewModel;

import lombok.Data;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:36
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class LoginReq {
    /**
     * 登录密码
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 账号
     */
    private String account;

    /**
     *  验证码
     */
    private String code;

    /**
     * 客户端请求验证码的token
     */
    private String clientToken;

    /**
     * 登录类型 0：后台；1：运营运维端；2：合作商后台
     */
    private Integer loginType;
}
