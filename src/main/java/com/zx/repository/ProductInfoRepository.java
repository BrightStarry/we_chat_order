package com.zx.repository;

import com.zx.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 97038 on 2017-07-23.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    /**
     * 通过商品状态查询所有商品
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
