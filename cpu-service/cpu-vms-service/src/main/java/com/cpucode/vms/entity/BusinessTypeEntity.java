package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商圈类型
 *
 * @author : cpucode
 * @date : 2021/10/20 11:12
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_business")
public class BusinessTypeEntity {
    /**
     * 主id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商圈名称
     */
    private String name;
}
