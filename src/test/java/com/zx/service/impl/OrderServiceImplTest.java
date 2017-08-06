package com.zx.service.impl;

import com.zx.domain.OrderDetail;
import com.zx.dto.OrderDTO;
import com.zx.enums.OrderStatusEnum;
import com.zx.enums.PayStatusEnum;
import com.zx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 订单服务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("郑星");
        orderDTO.setBuyerAddress("杭州");
        orderDTO.setBuyerPhone("17826824998");
        orderDTO.setBuyerOpenid("22");

        //订单详情

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(8);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(20);

        List<OrderDetail> orderDetailList = Arrays.asList(o1, o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}",result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1500994856061302911");
        log.info("【查询单个订单信息】result:{}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() throws Exception {
        Page<OrderDTO> page = orderService.findList("22", new PageRequest(0, 2));
        Assert.assertNotEquals(0,page.getTotalElements());

    }

    @Test
    public void cancel() throws Exception {
        //查询出OrderDTO
        OrderDTO orderDTO = orderService.findOne("1500994856061302911");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        //查询出OrderDTO
        OrderDTO orderDTO = orderService.findOne("1500994856061302911");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        //查询出OrderDTO
        OrderDTO orderDTO = orderService.findOne("1500994856061302911");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDTO.getPayStatus());
    }

}