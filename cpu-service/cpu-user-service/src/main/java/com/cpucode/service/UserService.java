package com.cpucode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.entity.UserEntity;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;
import com.cpucode.viewmodel.Pager;
import com.cpucode.viewmodel.UserViewModel;

import java.io.IOException;
import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 获取所有运营人员数量
     * @return 人员数量
     */
    Integer getOperatorCount();

    /**
     * 获取所有维修员数量
     * @return
     */
    Integer getRepairerCount();

    /**
     * 分页查询 用户信息
     * @param pageIndex 当前页
     * @param pageSize 页数
     * @param userName 用户名
     * @param roleId 角色id
     * @return
     */
    Pager<UserEntity> findPage(long pageIndex, long pageSize,
                                      String userName, Integer roleId);

    /**
     * 后台登录
     * @param req
     * @return
     */
    LoginResp login(LoginReq req) throws IOException;

    /**
     * 发送验证码
     * @param mobile 电话号码
     */
    void sendCode(String mobile);

    /**
     * 获取某区域下所有运营人员
     * @param regionId 区域id
     * @return
     */
    List<UserViewModel> getOperatorList(Long regionId);

    /**
     * 获取某区域下所有运维人员
     * @param regionId 区域id
     * @return
     */
    List<UserViewModel> getRepairerList(Long regionId);
}
