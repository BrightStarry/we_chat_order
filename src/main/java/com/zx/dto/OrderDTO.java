package com.zx.dto;

import com.zx.domain.OrderDetail;
import com.zx.domain.OrderMaster;
import com.zx.enums.OrderStatusEnum;
import com.zx.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    /** 订单主表*/

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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态，默认0，未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建时间 */
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;

    /** 订单详情列表*/
    private List<OrderDetail> orderDetailList;
}
