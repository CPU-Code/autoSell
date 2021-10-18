package com.cpucode.entiry;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共 Entity
 * @author : cpucode
 * @date : 2021/10/18 9:33
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
public class AbstractEntity implements Serializable {
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
}
