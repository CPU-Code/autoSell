package com.cpucode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.entity.PartnerEntity;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;

import java.io.IOException;

/**
 * 合作商接口
 *
 * @author : cpucode
 * @date : 2021/10/18 9:31
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface PartnerService extends IService<PartnerEntity> {
    /**
     * 合作商登录
     * @param req
     * @return
     * @throws IOException
     */
    LoginResp login(LoginReq req) throws IOException;

}
