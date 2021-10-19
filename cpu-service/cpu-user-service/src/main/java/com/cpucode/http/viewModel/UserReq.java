package com.cpucode.http.viewModel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/18 19:51
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class UserReq implements Serializable {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 所属区域Id
     */
    private String regionId;

    /**
     * 所属区域名
     */
    private String regionName;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 图片
     */
    private String image;
}
