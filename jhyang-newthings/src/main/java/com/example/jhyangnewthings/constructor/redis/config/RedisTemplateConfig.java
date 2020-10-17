//package com.example.jhyangnewthings.constructor.redis.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.time.Duration;
//
//import static com.example.jhyangnewthings.common.constant.NumConstant.IntNum.NUM_0;
//import static com.example.jhyangnewthings.common.constant.NumConstant.IntNum.NUM_2;
//
//@Configuration
//public class RedisTemplateConfig {
//
//    @Value("${spring.redis.base.host}")
//    private String baseHost;
//    @Value("${spring.redis.base.port}")
//    private String basePort;
//    @Value("${spring.redis.base.password}")
//    private String basePassword;
//    @Value("${spring.redis.base.database}")
//    private String baseDatabase;
//    @Value("${spring.redis.base.timeout}")
//    private String baseTimeout;
//    @Value("${spring.redis.base.maxTotal}")
//    private String baseMaxTotal;
//    @Value("${spring.redis.base.maxIdle}")
//    private String baseMaxIdle;
//    @Value("${spring.redis.base.minIdle}")
//    private String baseMinIdle;
//    @Value("${spring.redis.base.maxWaitMillis}")
//    private String baseMaxWaitMillis;
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(NUM_2)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(createJackson2JsonRedisSerializer())).disableCachingNullValues();
//        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
//        return cacheManager;
//
//    }
//
//
//    @Bean(name = "redisBaseTemplate")
//    public RedisTemplate redisBaseTemplate() {
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(connectionFactory(baseHost, Integer.parseInt(basePort), basePassword,
//                Integer.valueOf(baseTimeout), Integer.valueOf(baseMinIdle), Integer.valueOf(baseMaxIdle),
//                Integer.valueOf(baseMaxTotal), Long.valueOf(baseMaxWaitMillis), Integer.valueOf(baseDatabase)));
//        setRedisSerializeConfig(redisTemplate);
//        return redisTemplate;
//    }
//
//    /**
//     * 设置配置工厂
//     *
//     * @param host
//     * @param port
//     * @param password
//     * @param timeout
//     * @param minIdle
//     * @param maxIdle
//     * @param maxTotal
//     * @param maxWaitMillis
//     * @param database
//     * @return
//     */
//    @SuppressWarnings("all")
//    public RedisConnectionFactory connectionFactory(String host, int port, String password, int timeout,
//                                                    int minIdle, int maxIdle, int maxTotal, long maxWaitMillis,
//                                                    int database) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(host);
//        jedisConnectionFactory.setPort(port);
//        jedisConnectionFactory.setTimeout(timeout);
//        if (!StringUtils.isEmpty(password)) {
//            jedisConnectionFactory.setPassword(password);
//        }
//        if (database != NUM_0) {
//            jedisConnectionFactory.setDatabase(database);
//        }
//        jedisConnectionFactory.setPoolConfig(generatePoolConfig(minIdle, maxIdle, maxTotal, maxWaitMillis, true));
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//
//    /**
//     * 设置连接池配置
//     *
//     * @param minIdle
//     * @param maxIdle
//     * @param maxTotal
//     * @param maxWaitMillis
//     * @param flag
//     * @return
//     */
//    private JedisPoolConfig generatePoolConfig(int minIdle, int maxIdle, int maxTotal, long maxWaitMillis, boolean flag) {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        jedisPoolConfig.setTestOnBorrow(flag);
//        jedisPoolConfig.setTestOnReturn(flag);
//        jedisPoolConfig.setTestWhileIdle(flag);
//        return jedisPoolConfig;
//    }
//
//
//    /**
//     * 设置RedisTemplate的序列化方式
//     *
//     * @param redisTemplate
//     */
//    public void setRedisSerializeConfig(RedisTemplate redisTemplate) {
//        //设置Key的序列化方式
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //设置Value的序列化方式
//        redisTemplate.setValueSerializer(createJackson2JsonRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//    }
//
//    private Jackson2JsonRedisSerializer createJackson2JsonRedisSerializer() {
//        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        redisSerializer.setObjectMapper(objectMapper);
//        return redisSerializer;
//    }
//}
