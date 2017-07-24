package com.zx.repository;

import com.zx.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表
 * Created by dell on 2017/7/24.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /**
     * 查询用户(BuyerOpenid)订单,分页
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
