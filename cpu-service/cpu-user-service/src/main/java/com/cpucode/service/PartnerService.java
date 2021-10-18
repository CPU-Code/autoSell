package com.cpucode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.entity.PartnerEntity;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;
import com.cpucode.http.viewModel.PartnerReq;
import com.cpucode.http.viewModel.PartnerUpdatePwdReq;
import com.cpucode.viewmodel.Pager;

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


    /**
     * 更新合作商
     * @param id 主键id
     * @param req 合作商信息
     * @return
     */
    Boolean modify(Integer id, PartnerReq req);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 重置密码
     * @param id
     */
    void resetPwd(Integer id);

    /**
     * 查询合作商
     * @param pageIndex
     * @param pageSize
     * @param name
     * @return
     */
    Pager<PartnerEntity> search(Long pageIndex, Long pageSize, String name);

    /**
     * 更新密码
     * @param req
     * @return
     */
    Boolean updatePwd(Integer id, PartnerUpdatePwdReq req);
}
