package com.cpucode.common;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:23
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class VMSystem {
    /**
     * 管理员后台登录
     */
    public static final Integer LOGIN_ADMIN = 0;
    /**
     * 运维/运营人员app登录
     */
    public static final Integer LOGIN_EMP = 1;
    /**
     * 合作商登录
     */
    public static final Integer LOGIN_PARTNER = 2;

    /**
     * 客户端和合作商登录时用来生成 jwt token的secret
     */
    public static final String JWT_SECRET = "cpu+login+secret";

}
