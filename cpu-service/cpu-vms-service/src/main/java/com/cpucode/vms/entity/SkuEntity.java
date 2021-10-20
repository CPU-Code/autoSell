package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cpucode.entiry.AbstractEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:49
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_sku", autoResultMap = true, resultMap = "skuMap")
public class SkuEntity extends AbstractEntity implements Serializable {


}
