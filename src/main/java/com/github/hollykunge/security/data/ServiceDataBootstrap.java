package com.github.hollykunge.security.data;

import com.ace.cache.EnableAceCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author: zhhongyu
 * @description: 任务管理启动类
 * @since: Create in 9:37 2019/8/27
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.hollykunge.security.data.mapper")
@EnableAceCache
public class ServiceDataBootstrap {
    public static void main(String[] args) {
       SpringApplication.run(ServiceDataBootstrap.class, args);
    }
}
