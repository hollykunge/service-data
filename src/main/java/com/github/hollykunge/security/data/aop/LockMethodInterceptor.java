package com.github.hollykunge.security.data.aop;

import com.ace.cache.service.IRedisService;
import com.github.hollykunge.security.common.exception.BaseException;
import com.github.hollykunge.security.common.util.UUIDUtils;
import com.github.hollykunge.security.data.CacheKeyGenerator;
import com.github.hollykunge.security.data.annotation.RedisLock;
import com.github.hollykunge.security.data.dictionary.ErrorCodeEnum;
import com.github.hollykunge.security.data.exception.TaskBizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 分布式锁拦截器
 *
 * @author zhhongyu
 * @date 2019/11/9
 */
@Slf4j
@Aspect
@Configuration
public class LockMethodInterceptor {

    @Autowired
    public LockMethodInterceptor(CacheKeyGenerator cacheKeyGenerator) {
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    @Autowired
    private IRedisService iRedisService;
    private final CacheKeyGenerator cacheKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.github.hollykunge.security.data.annotation.RedisLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisLock lock = method.getAnnotation(RedisLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key can't be null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        try {
            //key不存在才能设置成功
            Long setnx = iRedisService.setnx(lockKey, UUIDUtils.generateShortUuid());
            if (setnx == 1) {
                //设置过期时间60秒，防止发生死锁
                iRedisService.set(lockKey, "", lock.expire());
            } else {
                //请勿重复提交表单
                throw new TaskBizException(ErrorCodeEnum.REDIS_LOCK);
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new BaseException(throwable);
            }
        } finally {
            iRedisService.del(lockKey);
        }
    }
}
