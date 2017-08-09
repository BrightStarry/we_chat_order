package com.zx.vo;

import lombok.Data;

/**
 * HTTP请求返回的最外层对象
 * Created by 97038 on 2017-07-23.
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    /** 状态码 */
    private Integer code;
    /** 消息 */
    private String msg;//消息
    /** 数据 */
    private T data;
}
