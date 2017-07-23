package com.zx.service.impl;

import com.zx.domain.ProductInfo;
import com.zx.enums.ProductStatusEnum;
import com.zx.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 97038 on 2017-07-23.
 * 商品
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("222");
        Assert.assertEquals("222",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,5);
        Page<ProductInfo> page = productService.findAll(request);
        System.out.println(page.getTotalElements());//总元素
        System.out.println(page.getTotalPages());//总页数
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111");
        productInfo.setProductName("雪碧");
        productInfo.setProductPrice(new BigDecimal(5.6));
        productInfo.setProductStock(15);
        productInfo.setProductDescription("冰爽一下");
        productInfo.setProductIcon("http://www.ss.jpgs");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(4);
        ProductInfo save = productService.save(productInfo);

        Assert.assertNotNull(save);
    }

}