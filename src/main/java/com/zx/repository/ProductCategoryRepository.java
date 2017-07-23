package com.zx.repository;

import com.zx.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 97038 on 2017-07-22.
 * 商品类目dao
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

    /**
     * 根据商品 类目编号集合 查询所有商品类目
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
}
