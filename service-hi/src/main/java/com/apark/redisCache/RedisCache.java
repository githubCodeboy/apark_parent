package com.apark.redisCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Component
public class RedisCache {



    @Autowired
    private JedisPool jedisPool  ;

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;



    JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

     //从redis缓存中查询，反序列化
    public Object getDataFromRedis(String redisKey){
          //查询
         Jedis jedis = jedisPool.getResource();
         RedisSerializer<String> serializer =   redisTemplate.getStringSerializer() ;
        RedisSerializer<Object> serializerValue = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
         byte[] result = jedis.get(serializer.serialize(redisKey ));


        //如果查询没有为空
        if(null == result){
           return null;
        }

         //查询到了，反序列化
         return serializerValue.deserialize(result);
    }

    //将数据库中查询到的数据放入redis
    public void setDataToRedis(String redisKey, Object obj ,Long expire){

        try {
            //序列化
            //存入redis
            Jedis jedis = jedisPool.getResource();
            RedisSerializer<String> serializer =   redisTemplate.getStringSerializer() ;
            RedisSerializer<Object> serializerValue = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
            String success = jedis.set(serializer.serialize(redisKey), serializerValue.serialize(obj));
            jedis.expire(redisKey,  expire.intValue());

            if("OK".equals(success)){
                 System.out.println("数据成功保存到redis...");
            }
        } finally {

        }
    }

}
