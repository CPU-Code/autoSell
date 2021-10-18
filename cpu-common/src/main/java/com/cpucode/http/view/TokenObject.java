package com.cpucode.http.view;

import lombok.Data;

/**
 * JWT令牌包装对象
 *
 * @author : cpucode
 * @date : 2021/10/17 22:42
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class TokenObject {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 登录类型 0：后台；1：运营运维端；2：合作商后台
     */
    private Integer loginType;
}
