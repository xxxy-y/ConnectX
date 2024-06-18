package cn.edu.tyut.connectx.auth.domain.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/13
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * 创建一个Jackson2JsonRedisSerializer实例，用于序列化和反序列化Redis中的Java对象为JSON格式。
     */
    private @NotNull Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        // 初始化Jackson2JsonRedisSerializer，指定要序列化的对象类型为Object
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 创建ObjectMapper实例，用于自定义序列化和反序列化的行为
        ObjectMapper objectMapper = new ObjectMapper();

        // 设置可见性，使所有属性都序列化和反序列化
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 配置ObjectMapper在反序列化时忽略未知属性，不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 启用默认类型，允许反序列化JSON到没有明确指定类型的Java对象
        // NON_FINAL选项允许使用非final类，JsonTypeInfo.As.PROPERTY表示类型信息作为JSON对象的一个属性
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // 将自定义的ObjectMapper设置到Jackson2JsonRedisSerializer中
        serializer.setObjectMapper(objectMapper);

        // 返回配置好的Jackson2JsonRedisSerializer实例
        return serializer;
    }
}
