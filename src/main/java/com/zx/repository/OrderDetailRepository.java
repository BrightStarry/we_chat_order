package com.zx.repository;

import com.zx.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情
 * Created by dell on 2017/7/24.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 查询一个订单下所有订单详情
     */
    List<OrderDetail> findByOrderId(String orderId);
}
