package com.cpucode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.common.VMSystem;
import com.cpucode.dao.PartnerDao;
import com.cpucode.entity.PartnerEntity;
import com.cpucode.exception.LogicException;
import com.cpucode.feignService.VMService;
import com.cpucode.http.view.TokenObject;
import com.cpucode.http.viewModel.LoginReq;
import com.cpucode.http.viewModel.LoginResp;
import com.cpucode.http.viewModel.PartnerReq;
import com.cpucode.http.viewModel.PartnerUpdatePwdReq;
import com.cpucode.service.PartnerService;
import com.cpucode.utils.BCrypt;
import com.cpucode.utils.JWTUtil;
import com.cpucode.viewmodel.Pager;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    private final VMService vmService;

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

    /**
     * 更新合作商
     * @param id 主键id
     * @param req 合作商信息
     * @return
     */
    @Override
    public Boolean modify(Integer id, PartnerReq req){
        LambdaUpdateWrapper<PartnerEntity> uw = new LambdaUpdateWrapper<PartnerEntity>();
        uw.set(PartnerEntity::getName, req.getName())
                .set(PartnerEntity::getRatio, req.getRatio())
                .set(PartnerEntity::getContact, req.getContact())
                .set(PartnerEntity::getPhone, req.getPhone());

        PartnerEntity partnerEntity = new PartnerEntity();
        BeanUtils.copyProperties(req, partnerEntity);
        partnerEntity.setId(id);

        return this.updateById(partnerEntity);
    }

    /**
     * 删除所属点
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        Integer nodeCount = vmService.getNodeCountByOwnerId(id);
        if(nodeCount == null)
            throw new LogicException("无法获取所属点位数");
        if(nodeCount > 0){
            throw new LogicException("请先修改下属点位归属");
        }

        return this.removeById(id);
    }

    /**
     * 重置密码
     * @param id
     */
    @Override
    public void resetPwd(Integer id) {
        String pwd = BCrypt.hashpw("123456", BCrypt.gensalt());
        LambdaUpdateWrapper<PartnerEntity> uw = new LambdaUpdateWrapper<PartnerEntity>();
        uw.set(PartnerEntity::getPassword, pwd)
                .eq(PartnerEntity::getId, id);

        this.update(uw);
    }

    /**
     * 查询合作商
     * @param pageIndex 当前页
     * @param pageSize 页码大小
     * @param name 名字
     * @return
     */
    @Override
    public Pager<PartnerEntity> search(Long pageIndex, Long pageSize, String name) {
        Page<PartnerEntity> page = new Page<>(pageIndex, pageSize);

        LambdaQueryWrapper<PartnerEntity> qw = new LambdaQueryWrapper<>();
        if(!Strings.isNullOrEmpty(name)){
            qw.like(PartnerEntity::getName, name);
        }

        this.page(page, qw);

        page.getRecords().forEach(p -> {
            p.setPassword("");
            p.setVmCount(vmService.getVmCountByOwnerId(p.getId()));
        });

        return Pager.build(page);
    }

    /**
     * 更新密码
     * @param req
     * @return
     */
    @Override
    public Boolean updatePwd(Integer id, PartnerUpdatePwdReq req){
        PartnerEntity partner = this.getById(id);
        if (partner == null){
            throw new LogicException("合作商不存在");
        }

        if (!BCrypt.checkpw(req.getPassword(), partner.getPassword())){
            throw new LogicException("原始密码错误");
        }

        LambdaUpdateWrapper<PartnerEntity> uw = new LambdaUpdateWrapper<>();
        uw.set(PartnerEntity::getPassword, BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()))
                .eq(PartnerEntity::getId, id);

        return this.update(uw);
    }

}
