package com.zx.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 97038 on 2017-07-22.
 * 商品类目
 */
//如果表名和实体类名不是驼峰对应的，可以使用如下注解
//@Table(name = "product_category")
@Entity
//下面这个注解是让Mysql中的timestamp字段能够在更新记录时自动更新
//如果不加，每次更新前要从表中查出原纪录，那么timestamp字段也是原值，然后更新的时候就会更新会原值
@DynamicUpdate
//下面的注解是lombok框架生成实体类getter/setter/toString方法的
@Data
public class ProductCategory {
    /** 类目id **/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /** 类目名 **/
    private String categoryName;
    /** 类目编号 **/
    private Integer categoryType;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }
}
