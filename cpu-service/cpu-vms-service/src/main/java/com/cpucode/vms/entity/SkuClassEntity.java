package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/20 11:22
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_sku_class")
public class SkuClassEntity implements Serializable {

    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    /**
     * 类别名称
     */
    @TableField(value = "class_name")
    private String className;

    /**
     * 上级id
     */
    @TableField(value = "parent_id")
    private Integer parentId;
}
