package com.cpucode.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

    /**
     * 商品id
     */
    @TableId(value = "sku_id", type = IdType.ASSIGN_ID)
    private Long skuId;

    /**
     * 商品名称
     */
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * 商品图片
     */
    @TableField(value = "sku_image")
    private String skuImage;

    /**
     * 商品类别Id
     */
    @TableField(value = "class_id")
    private Integer classId;

    /**
     * 商品品牌名称
     */
    private String brandName;

    /**
     * 基础价格
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 是否打折促销
     */
    @TableField(value = "is_discount")
    private boolean discount;

    /**
     * 净含量
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 商品类别
     */
    @TableField(exist = false)
    private SkuClassEntity skuClass;

    /**
     * 余量
     */
    @TableField(exist = false)
    private Integer capacity;

    /**
     * 商品真实售价
     */
    @TableField(exist = false)
    private Integer realPrice;

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final SkuEntity sku = (SkuEntity) obj;
        if (this == sku) {
            return true;
        } else {
            return this.skuId.equals(sku.skuId);
        }
    }

    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (skuId == null ? 0 : skuId.hashCode());

        return hashno;
    }

}
