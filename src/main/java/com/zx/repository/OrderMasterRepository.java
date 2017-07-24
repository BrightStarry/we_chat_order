package com.zx.repository;

import com.zx.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表
 * Created by dell on 2017/7/24.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

}
