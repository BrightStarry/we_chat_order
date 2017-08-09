package com.zx.enums;

/**
 * 有code属性的枚举接口
 * T 泛型是为了可以自定义Code属性的类型
 */
public interface CodeEnum<T> {
    T getCode();
}
