package com.zx.service.impl;

import com.zx.domain.ProductInfo;
import com.zx.dto.CartDTO;
import com.zx.enums.ProductStatusEnum;
import com.zx.enums.ResultEnum;
import com.zx.exception.SellException;
import com.zx.repository.ProductInfoRepository;
import com.zx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 97038 on 2017-07-23.
 * 商品
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return  productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }


    /**
     * 增加库存
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        //循环
        for (CartDTO cartDTO : cartDTOList) {
            //查询商品
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            //如果商品为空，抛出异常
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            //库存 + 要增加的数量
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            //设置
            productInfo.setProductStock(result);
            //保存
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 减少库存
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        //循环
        for (CartDTO cartDTO : cartDTOList) {
            //查询商品
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            //如果商品为空，抛出异常
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            //库存 - 要减少的数量
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            //如果减少后库存小于0，抛出异常
            if (result < 0)
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            //如果成功减少，则保存
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
