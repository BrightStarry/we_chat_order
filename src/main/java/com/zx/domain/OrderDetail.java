package com.zx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * 订单详情
 * Created by dell on 2017/7/24.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private String detailId;
    /** 商品id*/
    private String order_id;
    /** 商品id*/
    private String productId;
    /** 商品名称*/
    private String productName;
    /** 商品价格*/
    private BigDecimal productPrice;
    /** 商品数量*/
    private Integer productQuantity;
    /** 商品小图*/
    private String productIcon;

}
