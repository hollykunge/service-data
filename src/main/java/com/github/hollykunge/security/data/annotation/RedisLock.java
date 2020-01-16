package com.github.hollykunge.security.data.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Cache锁的注解
 * @author zhhongyu
 * @date 2019/11/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default "";

    /**
     * 过期秒数,默认为5秒
     *
     * @return 轮询锁的时间
     */
    int expire() default 5;

    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 超时时间，默认15秒
     * @return
     */
    int expireMillionSeconds() default 15;

    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:A001:500</p>
     *
     * @return String
     */
    String delimiter() default ":";
}
