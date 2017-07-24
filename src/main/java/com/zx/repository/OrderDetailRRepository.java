package com.zx.repository;

import com.zx.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单详情
 * Created by dell on 2017/7/24.
 */
public interface OrderDetailRRepository extends JpaRepository<OrderDetail,String> {
}
