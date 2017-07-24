package com.zx.service;

import com.zx.domain.OrderMaster;
import com.zx.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单
 */
public interface OrderService {
    /** 创建订单　*/
    OrderDTO create(OrderDTO orderDTO);
    /** 查询单个订单 */
    OrderMaster findOne(String orderId);
    /** 查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    /** 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);
    /** 完结订单 */
    OrderDTO finish(OrderDTO orderDTO);
    /** 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);
}
