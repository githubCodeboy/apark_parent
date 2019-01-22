package com.apark.redisCache;

import com.apark.service.Interface.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;


@Slf4j
@Aspect
@Component
public class GetCacheAOP {


    @Autowired
    private IRedisService  redisService;

    @Autowired
    private  RedisCache   redisCache;


    @Pointcut("@annotation(com.apark.redisCache.GetCache)")
    public void getCache(){
        log.info("  redis cache 切入点");
    }

    /**
     35      * 在所有标注@getCache的地方切入
     36      * @param joinPoint
     37      */
     @Around("getCache()")
     public Object beforeExec(ProceedingJoinPoint joinPoint){


              //前置：到redis中查询缓存
                System.out.println("调用从redis中查询的方法...");

               //redis中key格式: id
                 String redisKey = getCacheKey(joinPoint);

                //获取从redis中查询到的对象
                Object objectFromRedis = redisCache.getDataFromRedis(redisKey);

                //如果查询到了
               if(null != objectFromRedis){
                        System.out.println("从redis中查询到了数据...不需要查询数据库");
                        return objectFromRedis;
                     }

                 System.out.println("没有从redis中查到数据...");

                 //没有查到，那么查询数据库
                Object object = null;
                 try {
                        object = joinPoint.proceed();
                    } catch (Throwable e) {

                         e.printStackTrace();
                   }

                System.out.println("从数据库中查询的数据...");

               //后置：将数据库中查询的数据放到redis中
               log.info("调用把数据库查询的数据存储到redis中的方法...");
               MethodSignature ms=(MethodSignature) joinPoint.getSignature();
               Method method=ms.getMethod();
               Long  expire = method.getAnnotation(GetCache.class).expireTime();

                redisCache.setDataToRedis(redisKey, object ,expire);

               log.info("redis中的数据..."+object);
                 //将查询到的数据返回
                 return object;
          }



        /**
      * 根据类名、方法名和参数值获取唯一的缓存键
       * @return 格式为 "包名.类名.方法名.参数类型.参数值"，类似 "your.package.SomeService.getById(int).123"
       */

       @SuppressWarnings("unused")
        private String getCacheKey(ProceedingJoinPoint joinPoint) {
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        String ActionName = method.getAnnotation(GetCache.class).name();
        String fieldList = method.getAnnotation(GetCache.class).value();
        //System.out.println("签名是"+ms.toString());
        for (String field:fieldList.split(","))
            ActionName +="."+field;

            //先获取目标方法参数
            String id = null;
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                id = String.valueOf(args[0]);
             }

            ActionName += "="+id;
            String redisKey = ms+"."+ActionName;
             return redisKey;
      }
}
