package com.zx.service;

import com.zx.domain.ProductInfo;
import com.zx.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by 97038 on 2017-07-23.
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有上架商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询所有商品
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOList);
    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}
