package com.zx.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by 97038 on 2017-07-23.
 */
@Entity
@Data
public class ProductInfo {

    /** 商品id */
    @Id
    private String productId;
    /** 商品名 */
    private String productName;
    /** 单价 */
    private BigDecimal productPrice;
    /** 库存 */
    private Integer productStock;
    /** 描述 */
    private String productDescription;
    /** 小图 */
    private String productIcon;
    /** 状态 0正常，1下架*/
    private Integer productStatus;
    /** 类目编号 */
    private Integer categoryType;



}
