package com.zx.enums;

import lombok.Getter;

/**
 * 返回给前端的枚举
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(20,"库存异常"),
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(40,"订单详情不存在"),
    ORDER_STATUS_ERROR(50,"订单状态异常"),
    ORDER_UPDATE_FAIL(60,"订单更新失败"),
    ORDER_DETAIL_EMPTY(70,"订单无商品详情"),
    ORDER_PAY_STATUS_ERROR(80,"支付状态异常"),

    ORDER_OWNER_ERROR(90,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(100,"微信公众号异常"),
    ;


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
