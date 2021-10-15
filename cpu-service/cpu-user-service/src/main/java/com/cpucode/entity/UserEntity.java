package com.cpucode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:34
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_user")
public class UserEntity implements Serializable {
    /**
     * 主键自增 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色Id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 角色编号
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录名
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 私钥
     */
    @TableField(value = "secret")
    private String secret;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 所属区域Id
     */
    private Long regionId;

    /**
     * 所属区域名称
     */
    private String regionName;

    /**
     * 头像地址
     */
    private String image;

    /**
     * 是否启用
     */
    private Boolean status;

    @TableField(exist = false)
    private RoleEntity role;

}
