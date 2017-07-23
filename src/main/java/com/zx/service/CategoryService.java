package com.zx.service;

import com.zx.domain.ProductCategory;

import java.util.List;

/**
 * Created by 97038 on 2017-07-22.
 * 类目
 */
public interface CategoryService {
    /** 根据id查询单个类目 **/
    ProductCategory findOne(Integer categoryId);
    /** 查询所有类目 **/
    List<ProductCategory> findAll();
    /** 根据类目编号集合 查询 类目列表 **/
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    /** 保存类目 **/
    ProductCategory save(ProductCategory productCategory);
}
