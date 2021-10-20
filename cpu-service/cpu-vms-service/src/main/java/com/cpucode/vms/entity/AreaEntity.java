package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : cpucode
 * @date : 2021/10/19 15:36
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Data
@TableName(value = "tb_area")
public class AreaEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 父Id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 区域名称
     */
    @TableField(value = "area_name")
    private String areaName;

    /**
     * 地区编码
     */
    @TableField(value = "ad_code")
    private String adCode;

    /**
     * 城市区号
     */
    @TableField(value = "city_code")
    private String cityCode;

    /**
     * 地区级别
     */
    @TableField(value = "area_level")
    private String areaLevel;
}
