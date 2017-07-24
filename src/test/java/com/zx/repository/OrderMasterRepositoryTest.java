package com.zx.repository;

import com.zx.domain.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 订单主表
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid("2", pageRequest);
        System.out.println(page.getTotalElements());
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("杭州余杭");
        orderMaster.setBuyerName("王五");
        orderMaster.setBuyerOpenid("2");
        orderMaster.setBuyerPhone("17826824998");
        orderMaster.setOrderAmount(new BigDecimal("34.80"));
        orderMaster.setOrderId("3");
        orderMasterRepository.save(orderMaster);
    }

}