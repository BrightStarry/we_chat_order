package com.zx.repository;

import com.zx.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * Created by 97038 on 2017-07-22.
 * 商品类目dao 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne(){
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    //下面的注解表示 全部都回滚，让测试数据不保存，只测试成功与否
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("dssdfsfsd",9);
        ProductCategory save = repository.save(productCategory);
        Assert.assertNotNull(save);
    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = repository.findOne(2);
        productCategory.setCategoryName("dgsgdg");
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(3,6);
        List<ProductCategory> resultList = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,resultList.size());
    }
}