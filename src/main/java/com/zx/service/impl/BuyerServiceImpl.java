package com.zx.service.impl;

import com.zx.dto.OrderDTO;
import com.zx.enums.ResultEnum;
import com.zx.exception.SellException;
import com.zx.service.BuyerService;
import com.zx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 买家
 * 类似装饰者模式
 * 将OrderService的查询订单详情和取消订单详情进行扩展
 * 通过openid判断用户是否是同一用户
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        //查询
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null)
            return null;
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(orderId)) {
            log.error("【查询订单】openid不一致.oepnid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
