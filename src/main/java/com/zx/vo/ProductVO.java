package com.zx.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zx.domain.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * 商品返回对象(包含类目)
 * Created by 97038 on 2017-07-23.
 */
@Data
public class ProductVO {

    //该注解表示将此对象转为JSON格式时，该属性的key将为name
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
