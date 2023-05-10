package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Arte
 * @date 2020/3/26.
 */
@Component
public class RedisUtils {
    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 设置缓存键值对
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

    /**
     * 超时设置
     * @param key 键
     * @param value 值
     * @param time 时间值
     @param timeUnit 时间单位
     */
    public static void setex(String key, Object value, long time, TimeUnit timeUnit ) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value, time, timeUnit);
    }

    /**
     * 根据键拿缓存值
     * @param key
     * @return
     */
    public static Object get(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    /**
     * @param key key
     * @param type 返回值类型
     */
    public static <T> T get(String key, Class<T> type) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return JSON.parseObject(opsForValue.get(key).toString(), type);
    }

    /**
     * 检查 key 是否存在
     * @param key
     * @return
     */
    public static boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 自增
     * @param key
     * @return
     */
    public static long increment(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return opsForValue.increment(key, 1);
    }

    /**
     * 批量设置
     */
    public static void multiSet(Map<String, Object> map) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.multiSet(map);
    }

    /**
     * 批量获取
     */
    public static List<Object> multiGet(Collection<String> keys) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return opsForValue.multiGet(keys);
    }

    /**
     * 设置 hash
     * @param h hash
     * @param hk hash key
     * @param hv hash value
     */
    public static void putHash(String h, Object hk, Object hv) {
        redisTemplate.opsForHash().put(h, hk, hv);
    }

    /**
     * 获取 hash
     * @param h hash
     * @param hk hash key
     */
    public static Object getHash(String h, Object hk) {
        return redisTemplate.opsForHash().get(h, hk);
    }

    /**
     * 指定类型获取 hash
     * @param h hash
     * @param hk hash key
     * @param type 类型
     */
    public static <T> T getHash(String h, Object hk, Class<T> type) {
        return JSON.parseObject(redisTemplate.opsForHash().get(h, hk).toString(), type);
    }

    /**
     * 获取 hash 的 keys
     * @param h hash
     */
    public static Set<Object> keys(String h) {
        return redisTemplate.opsForHash().keys(h);
    }

    /**
     * 获取 hash 的大小
     * @param h hash
     */
    public static Long size(String h) {
        return redisTemplate.opsForHash().size(h);
    }

    /**
     * 获取 hash 所有的值
     * @param h hash
     */
    public static List<Object> values(String h) {
        return redisTemplate.opsForHash().values(h);
    }

    /**
     * 获取 hash 里所有的键值对
     * @param h hash
     */
    public static Map<Object, Object> entries(String h) {
        return redisTemplate.opsForHash().entries(h);
    }

    /**
     * 批量获取 hash 里的值
     * @param h hash
     * @param hashKeys 键集合
     */
    public static List<Object> multiGetHash(String h, Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(h, hashKeys);
    }

}
