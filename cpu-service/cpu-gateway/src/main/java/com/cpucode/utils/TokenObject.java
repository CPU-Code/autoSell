package com.cpucode.utils;

import lombok.Data;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:12
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class TokenObject {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 职能
     * */
    private String roleCode;

    /**
     * 公司名称
     */
    private int companyId;
}
