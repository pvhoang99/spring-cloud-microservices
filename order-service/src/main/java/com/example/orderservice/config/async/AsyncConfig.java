package com.example.orderservice.config.async;

import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Slf4j
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("Exception message - " + throwable.getMessage());
            log.error("Method name - " + method.getName());
        };
    }

}
