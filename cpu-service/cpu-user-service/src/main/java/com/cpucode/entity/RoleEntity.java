package com.cpucode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/14 20:46
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_role")
public class RoleEntity implements Serializable {
    /**
     * 主键自增 id
     */
    @TableId(value = "role_id",type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色编号
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;
}
