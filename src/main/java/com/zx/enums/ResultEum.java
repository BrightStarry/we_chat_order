package com.zx.enums;

import lombok.Getter;

/**
 * 返回给前端的枚举
 */
@Getter
public enum ResultEum {

    PRODUCT_NOT_EXIST(10,"商品不存在")
    ;


    private Integer code;
    private String message;

    ResultEum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
