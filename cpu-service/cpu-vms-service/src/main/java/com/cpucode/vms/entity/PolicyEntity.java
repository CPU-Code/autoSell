package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpucode.entiry.AbstractEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 策略
 * @author : cpucode
 * @date : 2021/11/2 19:31
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_policy")
public class PolicyEntity extends AbstractEntity implements Serializable {
    /**
     * 策略id
     */
    @TableId(value = "policy_id", type = IdType.AUTO)
    private Integer policyId;

    /**
     * 策略名称
     */
    @TableField(value = "policy_name")
    private String policyName;

    /**
     * 折扣，如：80代表8折
     */
    @TableField(value = "discount")
    private Integer discount;
}
