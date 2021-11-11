package com.cpucode.vms.entity;

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
}
