package com.zx.repository;

import com.zx.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 订单详情
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("2");
        orderDetail.setOrderId("1");
        orderDetail.setProductIcon("xxx.jpg");
        orderDetail.setProductId("3");
        orderDetail.setProductName("苹果");
        orderDetail.setProductPrice(new BigDecimal("8.9"));
        orderDetail.setProductQuantity(999);

        OrderDetail result = orderDetailRRepository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> list = orderDetailRRepository.findByOrderId("1");
        Assert.assertNotEquals(0,list.size());
    }

}