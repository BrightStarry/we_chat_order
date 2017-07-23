package com.zx.repository;

import com.zx.domain.ProductInfo;
import com.zx.enums.ProductStatusEnum;
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
 * Created by 97038 on 2017-07-23.
 *
 * 商品信息
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("222");
        productInfo.setProductName("西瓜");
        productInfo.setProductPrice(new BigDecimal(8.1));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("大西瓜");
        productInfo.setProductIcon("http://www.ss.jpgs");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(3);

        repository.save(productInfo);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());

        List<ProductInfo> list2 = repository.findByProductStatus(1);
        Assert.assertEquals(0,list2.size());
    }

}