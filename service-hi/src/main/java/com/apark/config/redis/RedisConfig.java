package com.apark.config.redis;/*
package com.aytest.config.redis;

import com.aytest.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {

    private static Logger logger = Logger.getLogger(RedisConfig.class);

    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private int  port;



 */
/*   @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer mmaxRedirectsac;*//*


 */
/**
 * JedisPoolConfig 连接池
 *
 * @return 单机版配置
 * @param @param  jedisPoolConfig
 * @param @return
 * @return JedisConnectionFactory
 * @throws
 * @Title: JedisConnectionFactory
 * @autor lpl
 * @date 2018年2月24日
 * <p>
 * 实例化 RedisTemplate 对象
 * @return 设置数据存入 redis 的序列化方式,并开启事务
 * @param redisTemplate
 * @param factory
 * <p>
 * 注入封装RedisTemplate
 * @return RedisUtil
 * @throws
 * @Title: redisUtil
 * @autor lpl
 * @date 2017年12月21日
 *//*

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    */
/**
 * 单机版配置
 *
 * @param @param  jedisPoolConfig
 * @param @return
 * @return JedisConnectionFactory
 * @throws
 * @Title: JedisConnectionFactory
 * @autor lpl
 * @date 2018年2月24日
 *//*

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.setUsePool(true);
        //连接池
        //jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        JedisPoolConfig config = jedisPoolConfig();
        jedisConnectionFactory.setPoolConfig(config);
        //IP地址
        jedisConnectionFactory.setHostName("127.0.0.1");
        //端口号
        jedisConnectionFactory.setPort(port);
        //如果Redis设置有密码
        jedisConnectionFactory.setPassword(password);
        //客户端超时时间单位是毫秒
        jedisConnectionFactory.setTimeout(5000);

        logger.info("JedisConnectionFactory bean init success.");
        return jedisConnectionFactory;
    }


    @Bean(name = "jedisTemplate")
    public RedisTemplate jedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate jedisTemplate = new RedisTemplate();
        jedisTemplate.setConnectionFactory(jedisConnectionFactory);

        jedisTemplate.setValueSerializer(new StringRedisSerializer());
        jedisTemplate.setKeySerializer(new StringRedisSerializer());

        return jedisTemplate;
    }



    */
/**
 * 实例化 RedisTemplate 对象
 *
 * @return
 *//*

 */
/* @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
       // initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
       redisTemplate.setEnableTransactionSupport(true);
       redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }*//*


 */
/**
 * 设置数据存入 redis 的序列化方式,并开启事务
 *
 * @param redisTemplate
 * @param factory
 *//*

 */
/* private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
     *//*
 */
/*   redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());*//*
 */
/*

        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }*//*


 */
/**
 * 注入封装RedisTemplate
 *
 * @return RedisUtil
 * @throws
 * @Title: redisUtil
 * @autor lpl
 * @date 2017年12月21日
 *//*

    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate  jedisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(jedisTemplate);
        return redisUtil;
    }
}
*/
