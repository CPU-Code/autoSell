package com.cpucode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.common.VMSystem;
import com.cpucode.dao.PartnerDao;
import com.cpucode.entity.PartnerEntity;
import com.cpucode.http.view.TokenObject;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;
import com.cpucode.service.PartnerService;
import com.cpucode.utils.BCrypt;
import com.cpucode.utils.JWTUtil;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author : cpucode
 * @date : 2021/10/18 9:31
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl extends ServiceImpl<PartnerDao, PartnerEntity> implements PartnerService {
    /**
     * RequiredArgsConstructor 可以 代替所有的 Autowired
     */
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 合作商登录
     * @param req
     * @return
     * @throws IOException
     */
    @Override
    public LoginResp login(LoginReq req) throws IOException {
        LoginResp resp = new LoginResp();
        resp.setSuccess(false);

        String code = redisTemplate.opsForValue().get(req.getClientToken());
        if(Strings.isNullOrEmpty(code)){
            resp.setMsg("验证码错误");
            return resp;
        }
        if(!req.getCode().equals(code)){
            resp.setMsg("验证码错误");
            return resp;
        }

        // 查询该用户
        QueryWrapper<PartnerEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(PartnerEntity::getAccount, req.getAccount());
        PartnerEntity partnerEntity = this.getOne(qw);

        if(partnerEntity == null){
            resp.setMsg("不存在该账户");
            return resp;
        }
        if(!BCrypt.checkpw(req.getPassword(), partnerEntity.getPassword())){
            resp.setMsg("账号或密码错误");
            return resp;
        }

        resp.setSuccess(true);
        resp.setUserName(partnerEntity.getName());
        resp.setUserId(partnerEntity.getId());
        resp.setMsg("登录成功");

        TokenObject tokenObject = new TokenObject();
        tokenObject.setUserId(partnerEntity.getId());
        tokenObject.setLoginType(VMSystem.LOGIN_PARTNER);
        tokenObject.setMobile(partnerEntity.getMobile());

        String token = JWTUtil.createJWTByObj(tokenObject, partnerEntity.getMobile() + VMSystem.JWT_SECRET);
        resp.setToken(token);

        return resp;
    }
}
