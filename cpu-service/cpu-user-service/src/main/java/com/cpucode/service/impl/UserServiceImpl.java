package com.cpucode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.common.VMSystem;
import com.cpucode.dao.UserDao;
import com.cpucode.entity.UserEntity;
import com.cpucode.http.view.TokenObject;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;
import com.cpucode.service.UserService;
import com.cpucode.utils.BCrypt;
import com.cpucode.utils.JWTUtil;
import com.cpucode.viewmodel.Pager;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author : cpucode
 * @date : 2021/10/15 22:15
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取所有运营人员数量
     * @return 人员数量
     */
    @Override
    public Integer getOperatorCount(){
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        // TODO 修改查询值
        wrapper.eq(UserEntity::getRoleCode, "1002");

        return this.count(wrapper);
    }

    /**
     * 获取所有维修员数量
     * @return
     */
    @Override
    public Integer getRepairerCount(){
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        // TODO 修改查询值
        wrapper.eq(UserEntity::getRoleCode, "1003");

        return this.count(wrapper);
    }

    /**
     * 分页查询 用户信息
     * @param pageIndex 当前页
     * @param pageSize 页数
     * @param userName 用户名
     * @param roleId 角色id
     * @return
     */
    @Override
    public Pager<UserEntity> findPage(long pageIndex, long pageSize,
                                      String userName, Integer roleId){
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserEntity> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        if (Strings.isNullOrEmpty(userName)){
            wrapper.like(UserEntity::getUserName, userName);
        }

        if (roleId != null && roleId > 0){
            wrapper.eq(UserEntity::getRoleId, 1);
        }

        // TODO 不懂
        wrapper.ne(UserEntity::getRoleId, 1);

        this.page(page, wrapper);
        page.getRecords().forEach(u -> {
            u.setPassword("");
            u.setSecret("");
        });

        return Pager.build(page);
    }


    /**
     * 后台登录
     * @param req
     * @return
     */
    @Override
    public LoginResp login(LoginReq req) throws IOException{
        if (req.getLoginType() == VMSystem.LOGIN_ADMIN){
            return this.adminLogin(req);
        } else if (req.getLoginType() == VMSystem.LOGIN_EMP){
            return this.empLogin(req);
        } else if (req.getLoginType() == VMSystem.LOGIN_PARTNER){
            return partnerService.login(req);
        }

        LoginResp resp = new LoginResp();
        resp.setSuccess(false);
        resp.setMsg("不存在该账户");

        return resp;
    }


    /**
     * 管理员登录
     *
     * @param req
     * @return
     * @throws IOException
     */
    private LoginResp adminLogin(LoginReq req) throws IOException {
        LoginResp resp = new LoginResp();
        resp.setSuccess(false);

        // 通过 redis 获取验证码
        String code = redisTemplate.boundValueOps(req.getClientCode()).get();

        if (Strings.isNullOrEmpty(code)){
            resp.setMsg("验证码错误");
            return resp;
        }
        if(!req.getCode().equals(code)){
            resp.setMsg("验证码错误");
            return resp;
        }

        // 通过登录名获取 用户消息
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(UserEntity::getLoginName, req.getLoginName());

        UserEntity userEntity = this.getOne(qw);
        if(userEntity == null){
            resp.setMsg("账户名或密码错误");
            return resp;
        }

        // 验证密码
        boolean loginSuccess = BCrypt.checkpw(req.getPassword(), userEntity.getPassword());
        if(!loginSuccess){
            resp.setMsg("账户名或密码错误");
            return resp;
        }

        return okResp(userEntity, VMSystem.LOGIN_ADMIN);
    }

    /**
     * 运维运营人员登录
     * @param req
     * @return
     * @throws IOException
     */
    private LoginResp empLogin(LoginReq req) throws IOException {
        LoginResp resp = new LoginResp();
        resp.setSuccess(false);

        // 通过 redis 获取验证码
        String code = redisTemplate.boundValueOps(req.getMobile()).get();
        if(Strings.isNullOrEmpty(code)){
            resp.setMsg("验证码错误");
            return resp;
        }
        if(!req.getCode().equals(code)){
            resp.setMsg("验证码错误");
            return resp;
        }

        // 通过电话号码获取 用户消息
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.lambda()
                .eq(UserEntity::getMobile, req.getMobile());

        UserEntity userEntity = this.getOne(qw);
        if (userEntity == null){
            resp.setMsg("不存在该账户");
            return resp;
        }

        return okResp(userEntity, VMSystem.LOGIN_EMP);
    }


    /**
     * 登录成功签发token
     * @param userEntity
     * @param loginType
     * @return
     */
    private LoginResp okResp(UserEntity userEntity, Integer loginType) throws IOException {
        LoginResp resp = new LoginResp();
        resp.setSuccess(true);
        resp.setRoleCode(userEntity.getRoleCode());
        resp.setUserName(userEntity.getUserName());
        resp.setUserId(userEntity.getId());
        resp.setRegionId(userEntity.getRegionId()+"");
        resp.setMsg("登录成功");

        TokenObject tokenObject = new TokenObject();
        tokenObject.setUserId(userEntity.getId());
        tokenObject.setMobile(userEntity.getMobile());
        tokenObject.setLoginType(loginType);

        String token = JWTUtil.createJWTByObj(tokenObject,userEntity.getMobile() + VMSystem.JWT_SECRET);
        resp.setToken(token);
        return resp;
    }

}
