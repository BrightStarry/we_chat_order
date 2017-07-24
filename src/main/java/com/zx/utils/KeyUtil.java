package com.zx.utils;

import java.util.Random;

/**
 * 生成key工具类
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String generateUniqueKey() {
        //生成长度为6位的随机数
        Integer i = new Random().nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(i);
    }
}
