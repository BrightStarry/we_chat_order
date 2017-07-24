package com.zx.exception;

import com.zx.enums.ResultEum;

/**
 * 系统自定义异常
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEum resultEum) {
        super(resultEum.getMessage());
        this.code = resultEum.getCode();
    }
}
