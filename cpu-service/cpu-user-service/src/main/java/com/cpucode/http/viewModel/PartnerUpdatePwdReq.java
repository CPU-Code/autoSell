package com.cpucode.http.viewModel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/18 18:40
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class PartnerUpdatePwdReq implements Serializable {
    /**
     * 密码
     */
    private String password;

    /**
     * 新密码
     */
    private String newPassword;
}
