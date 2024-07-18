package com.jiwoong.billingsystem.global.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.lang.annotation.Annotation;


@Configuration
public class RedisConfig implements CacheConfig {

    @Value("localhost")
    private String host;

    @Value("6379")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Override
    public String[] cacheNames() {
        return new String[0];
    }

    @Override
    public String keyGenerator() {
        return null;
    }

    @Override
    public String cacheManager() {
        return null;
    }

    @Override
    public String cacheResolver() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
