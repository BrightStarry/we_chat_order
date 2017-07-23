package com.zx.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by 97038 on 2017-07-23.
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
