package com.zx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by dell on 2017/7/24.
 * 订单主表
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMaster {
    private String orderId;
    /** 买家姓名*/
    private String buyerName;
    /** 买家电话 */
    private String buyerPhone;
    /** 买家地址 */
    private String buyerAddress;
    /** 买家微信openid */
    private String buyerOpenid;
    /** 订单总金额*/
    private BigDecimal orderAmount;
    /** 订单状态，默认0，新下单*/
    private Integer orderStatus;
    /** 支付状态，默认0，未支付*/
    private Integer payStatus;

}
