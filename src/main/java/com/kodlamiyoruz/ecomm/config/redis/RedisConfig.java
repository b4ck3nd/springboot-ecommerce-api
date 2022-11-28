package com.kodlamiyoruz.ecomm.config.redis;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration lettuce=LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(25))
                .shutdownTimeout(Duration.ZERO)
                .build();
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost",6379),lettuce);
    }

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory factory) {
        Duration expiration=Duration.ofSeconds(25);
        //RedisCacheManager.RedisCacheManagerBuilder builder=RedisCacheManager.builder(factory).cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration));
        //return builder.build();
        return  RedisCacheManager.builder(factory).cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration)).build();
    }
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        StringRedisSerializer stringRedisTemplate=new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(stringRedisTemplate);
        redisTemplate.setHashKeySerializer(stringRedisTemplate);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
