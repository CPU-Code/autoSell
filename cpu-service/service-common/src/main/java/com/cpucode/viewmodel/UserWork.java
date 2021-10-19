package com.cpucode.viewmodel;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户工作量
 * @author : cpucode
 * @date : 2021/10/19 13:27
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class UserWork implements Serializable {
    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户角色名
     */
    private String roleName;

    /**
     * 完成工单数
     */
    private Integer workCount;

    /**
     * 当日进行中的工单
     */
    private Integer progressTotal;

    /**
     * 拒绝工单数
     */
    private Integer cancelCount;
}
