package com.example.common.hytrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.security.SecurityContextConcurrencyStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadLocalConfiguration {

  @Setter(onMethod = @__({@Autowired}))
  private HystrixConcurrencyStrategy hystrixConcurrencyStrategy;

  @PostConstruct
  public void init() {

    HystrixEventNotifier hystrixEventNotifier = HystrixPlugins.getInstance().getEventNotifier();
    HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
    HystrixPropertiesStrategy hystrixPropertiesStrategy = HystrixPlugins.getInstance()
        .getPropertiesStrategy();
    HystrixCommandExecutionHook hystrixCommandExecutionHook = HystrixPlugins.getInstance()
        .getCommandExecutionHook();
    HystrixPlugins.reset();

    HystrixPlugins.getInstance()
        .registerConcurrencyStrategy(new ThreadLocalAwareStrategy(hystrixConcurrencyStrategy));
    HystrixPlugins.getInstance().registerEventNotifier(hystrixEventNotifier);
    HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
    HystrixPlugins.getInstance().registerPropertiesStrategy(hystrixPropertiesStrategy);
    HystrixPlugins.getInstance().registerCommandExecutionHook(hystrixCommandExecutionHook);


  }

}
