package com.example.chat.config.async;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

  @Override
  @Bean("executor")
  @Primary
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(30);
    taskExecutor.setMaxPoolSize(40);
    taskExecutor.setQueueCapacity(10);
    taskExecutor.initialize();
    return taskExecutor;

  }

}
