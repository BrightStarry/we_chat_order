package com.zx.utils;

import com.zx.enums.CodeEnum;

/**
 * 枚举工具类
 *
 */
public class EnumUtil {

    /**
     * 根据Code返回Message
     * <T extends CodeEnum>相当于一种修饰符，表明T这个泛型，必须继承自CodeEnum
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
