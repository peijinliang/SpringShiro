package com.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import javax.annotation.Resource;

/**
 * Crete by Marlon
 * Create Date: 2018/6/29
 * Class Describe
 * Redis 实现CacheManger
 **/

public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println("获取RedisCache缓存");
        return redisCache;
    }

}
