package com.baizhi.wb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Set;

@Configuration
@Aspect
public class addResidAspect {
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    //添加缓存
   @Around("@annotation(com.baizhi.wb.annotcation.AddRedis))")
    public Object addRedis(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("添加Redis的环绕通知");
        //序列化解决乱码
        StringRedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        StringBuilder sb=new StringBuilder();
        //获取类名
        String className=proceedingJoinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName=proceedingJoinPoint.getSignature().getName();
        sb.append(methodName);
        //获取参数
        Object[] args=proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg);
        }
        String key=sb.toString();
        //判断key是否存在
       HashOperations hashOperations = redisTemplate.opsForHash();
       Boolean aBoolean = hashOperations.hasKey(className,key);

        Object result=null;
        if (aBoolean){
            //key存在
            result=hashOperations.get(className,key);
        }else {
            //不存在
            //放行方法
            try {
                result=proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            hashOperations.put(className,key,result);
        }
        return result;
    }
    @After("@annotation(com.baizhi.wb.annotcation.DelRedis)")
    public void delRedis(JoinPoint joinPoint){
        System.out.println("清除缓存的后置通知");
        //获取类的全限定名
        String className=joinPoint.getTarget().getClass().getName();
        Set<String> keys=stringRedisTemplate.keys("*");
        for (String key : keys) {
            System.out.println("key="+key);
            if (key.startsWith(className)){
                redisTemplate.delete(key);
            }
        }
    }
}
