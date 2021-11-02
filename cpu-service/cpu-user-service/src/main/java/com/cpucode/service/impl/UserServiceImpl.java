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
import com.cpucode.service.PartnerService;
import com.cpucode.service.UserService;
import com.cpucode.sms.SmsSender;
import com.cpucode.utils.BCrypt;
import com.cpucode.utils.JWTUtil;
import com.cpucode.viewmodel.Pager;
import com.cpucode.viewmodel.UserViewModel;
import com.google.common.base.Strings;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private SmsSender smsSender;

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
     * @return 用户分页
     */
    @Override
    public Pager<UserEntity> findPage(long pageIndex, long pageSize,
                                      String userName, Integer roleId){
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserEntity> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);

        // 用户名模糊查询
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        if (Strings.isNullOrEmpty(userName)){
            wrapper.like(UserEntity::getUserName, userName);
        }

        if (roleId != null && roleId > 0){
            wrapper.eq(UserEntity::getRoleId, roleId);
        }

        // TODO 不懂 ,,,,,, 不等于 1
        wrapper.ne(UserEntity::getRoleId, 1);

        this.page(page, wrapper);

        // 设置敏感内容为空
        page.getRecords().forEach(u -> {
            u.setPassword("");
            u.setSecret("");
        });

        return Pager.build(page);
    }


    /**
     * 后台登录
     * @param req 登录请求
     * @return 登录响应
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

        // 错误返回
        LoginResp resp = new LoginResp();
        resp.setSuccess(false);
        resp.setMsg("不存在该账户");

        return resp;
    }

    /**
     * 发送验证码
     * @param mobile 电话号码
     */
    @Override
    public void sendCode(String mobile){
        //非空校验
        if(Strings.isNullOrEmpty(mobile)) return;

        //查询用户表中是否存在该手机号
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getMobile, mobile);

        //如果不存在，直接返回
        if(this.count(wrapper) <= 0) {
            return ;
        }

        //利用 redis过期时间 , 避免5分钟内重复发送
        if(redisTemplate.opsForValue().get(mobile) != null) {
            return;
        }

        //生成5位短信验证码
        StringBuilder sbCode = new StringBuilder();
        Stream.generate(() -> new Random().nextInt(10))
                .limit(5)
                .forEach(x -> sbCode.append(x));

        //将验证码放入 redis ，5分钟过期
        redisTemplate.opsForValue().set(mobile, sbCode.toString(), Duration.ofMinutes(5));

        //发送短信
        smsSender.sendMsg(mobile, sbCode.toString());
    }

    /**
     * 获取某区域下所有运营人员
     * @param regionId 区域id
     * @return
     */
    @Override
    public List<UserViewModel> getOperatorList(Long regionId){
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        // TODO 编号
        wrapper.eq(UserEntity::getRoleCode, "1002")
                .eq(UserEntity::getRegionId, regionId)
                .eq(UserEntity::getStatus, true);

        return this.list(wrapper).stream().map(u -> {
                    UserViewModel vo = new UserViewModel();

                    BeanUtils.copyProperties(u, vo);

                    // TODO 查询不到??
                    vo.setRoleName(u.getRole().getRoleName());

                    vo.setRoleCode(u.getRoleCode());
                    vo.setUserId(u.getId());

                    return vo;
                }).collect(Collectors.toList());
    }

    /**
     * 获取某区域下所有运维人员
     * @param regionId 区域id
     * @return
     */
    @Override
    public List<UserViewModel> getRepairerList(Long regionId) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getRoleCode, "1003")
                .eq(UserEntity::getRegionId, regionId)
                .eq(UserEntity::getStatus, true);

        return this.list(wrapper).stream().map(u -> {
                    UserViewModel vo = new UserViewModel();

                    BeanUtils.copyProperties(u, vo);

                    // TODO 查询不到??
                    vo.setRoleName(u.getRole().getRoleName());

                    vo.setRoleCode(u.getRoleCode());
                    vo.setUserId(u.getId());

                    return vo;
                }).collect(Collectors.toList());
    }

    /**
     * 获取某区域下维修员/运营员总数
     * @param regionId 区域id
     * @param isRepair 切换角色
     * @return
     */
    @Override
    public Integer getCountByRegion(Long regionId, Boolean isRepair){
        // 根据区域id
        LambdaQueryWrapper<UserEntity> qw = new LambdaQueryWrapper<UserEntity>();
        qw.eq(UserEntity::getRegionId, regionId);

        if(isRepair){
            qw.eq(UserEntity::getRoleId, 3);
        }else {
            qw.eq(UserEntity::getRoleId, 2);
        }

        return this.count(qw);
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
        String code = redisTemplate.boundValueOps(req.getClientToken()).get();

        if (Strings.isNullOrEmpty(code) || !req.getCode().equals(code)){
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
     * @param req 登录入参
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
     * @param userEntity  用户信息
     * @param loginType  登录类型 0：后台；1：运营运维端；2：合作商后台
     * @return 登录响应信息
     */
    private LoginResp okResp(UserEntity userEntity, Integer loginType) throws IOException {
        LoginResp resp = new LoginResp();
        resp.setSuccess(true);
        resp.setRoleCode(userEntity.getRoleCode());
        resp.setUserName(userEntity.getUserName());
        resp.setUserId(userEntity.getId());
        resp.setRegionId(userEntity.getRegionId()+"");
        resp.setMsg("登录成功");

        // JWT构造
        TokenObject tokenObject = new TokenObject();
        tokenObject.setUserId(userEntity.getId());
        tokenObject.setMobile(userEntity.getMobile());
        tokenObject.setLoginType(loginType);

        String token = JWTUtil.createJWTByObj(tokenObject,userEntity.getMobile() + VMSystem.JWT_SECRET);
        resp.setToken(token);
        return resp;
    }

}
