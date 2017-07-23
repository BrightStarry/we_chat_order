package com.zx.service.impl;

import com.zx.domain.ProductCategory;
import com.zx.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 97038 on 2017-07-22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory one = categoryService.findOne(3);
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = categoryService.findAll();
        Assert.assertNotEquals(0,list.size());

    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(3,6);
        List<ProductCategory> resultList = categoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,resultList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("ddd", 515);
        ProductCategory save = categoryService.save(productCategory);
        Assert.assertNotNull(save);
    }

}