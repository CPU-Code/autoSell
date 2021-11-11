package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpucode.entiry.AbstractEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 机器和策略关联
 *
 * @author : cpucode
 * @date : 2021/11/11 13:44
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_vm_policy")
public class VmPolicyEntity extends AbstractEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 售货机id
     */
    @TableField(value = "vm_id")
    private Long vmId;

    /**
     * 售货机编号
     */
    @TableField(value = "inner_code")
    private String innerCode;

    /**
     * 策略id
     */
    @TableField(value = "policy_id")
    private Integer policyId;

    /**
     * 策略名称
     */
    @TableField(value = "policy_name")
    private String policyName;

    /**
     * 折扣
     */
    @TableField(value = "discount")
    private Integer discount;
}
