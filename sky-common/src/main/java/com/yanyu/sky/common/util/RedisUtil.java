package com.yanyu.sky.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yanyu
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public RedisTemplate getInstance() {
        return redisTemplate;
    }
}
