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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Aspect
@Component
public class GetCacheAOP {


    @Autowired
    private IRedisService  redisService;

    @Autowired
    private  RedisCache   redisCache;

   /* ExecutorService fixedThreadPool = Executors.newFixedThreadPool(200);*/


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
                 log.info("调用从redis中查询的方法...");
                 MethodSignature ms=(MethodSignature) joinPoint.getSignature();
                 Method method=ms.getMethod();
                 Long  expire = method.getAnnotation(GetCache.class).expireTime();
                //redis中key格式: id
                 String redisKey = getCacheKey(joinPoint);
                //缓存标记
                String cacheSign = redisKey + "_sign";
                //获取从redis中查询到的对象
                Object objectFromRedis = redisCache.getDataFromRedis(redisKey);
                String sign =  (String)redisCache.getDataFromRedis(cacheSign);
                //如果查询到了
               if(null != sign){
                        log.info("----------- redis缓存击中， 返回缓存值：{}" ,objectFromRedis);
                        return objectFromRedis;
                 }
                 else{
                       log.info("没有从redis中查到数据.查询数据");
                       redisCache.setDataToRedis(cacheSign, "1" ,expire);
                       //没有查到，那么查询数据库
                       Object object = null;

                  /* fixedThreadPool.execute((new Runnable() {
                       public void run() {
                           try {
                               object = joinPoint.proceed();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                   });*/
                       try {
                           object = joinPoint.proceed();
                       } catch (Throwable e) {

                           e.printStackTrace();
                       }
                       //后置：将数据库中查询的数据放到redis中
                       //***** 将数据库查询的 内容添加到redis 缓存
                       redisCache.setDataToRedis(redisKey, object ,expire * 2);
                       log.info("redis中的数据..."+object);
                       //将查询到的数据返回
                       return object;
                   }

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
