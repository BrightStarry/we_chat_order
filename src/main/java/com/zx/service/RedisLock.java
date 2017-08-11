package com.zx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis分布式锁
 */
@Slf4j
@Component
public class RedisLock {


    //可以直接注入这个对象redisTemplate.opsForValue()获取的对象
    @Autowired
    ValueOperations<String, String> valueOperations;
    /**
     *  加锁
     *  如果加锁失败，可以直接抛出异常，提醒用户，访问人数过多
     * @param key
     * @param value 当前时间 + 超时时间（long）,例如 11111 + 100，也就表示了过期时间
     * @return
     */
    public boolean lock(String key, String value) {
        //如果key不存在，则set
        //如果可以成功设置的话
        if (valueOperations.setIfAbsent(key, value))
            return true;
        /**
         * 下列代码判断过期时间，解决死锁
         */
        //如果被锁了
        //获取当前锁的过期时间
        String currentValue = valueOperations.get(key);
        //与当前时间比较，如果锁过期，设置新锁
        if (!StringUtils.isEmpty(currentValue)
                && Long.valueOf(currentValue) < System.currentTimeMillis()) {
            /**
             * 下列代码保证并发情况下的锁的线程安全
             * getAndSet()方法是同步的，因为redis是单线程的
             * 当多个线程同时调用该代码，只有第一个调用getAndSet()方法的线程才可以获取到
             * 与currentValue相同的oldValue，才会返回true，表示获取到锁；
             *
             * 当然，存在的一个缺陷就是后面的几个线程继续修改该锁的过期时间，
             * 导致该锁的过期时间比预期的稍微晚一点
             */
            String oldValue = valueOperations.getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue)
                    && oldValue.equals(currentValue))
                return true;
        }
        //否则返回false
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unLock(String key, String value) {
        try {
            /**
             * 比较redis中该锁的value和自己携带的该锁value是否相同
             * 如果相同，删除该锁
             */
            String currentValue = valueOperations.get(key);
            if (!StringUtils.isEmpty(currentValue)
                    && currentValue.equals(value)) {
                valueOperations.getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常.e={}",e);
        }
    }


}
