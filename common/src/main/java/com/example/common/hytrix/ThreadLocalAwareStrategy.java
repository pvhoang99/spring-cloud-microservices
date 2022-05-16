package com.example.common.hytrix;

import com.example.common.utils.UserContextHolder;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {

  private final HystrixConcurrencyStrategy hystrixConcurrencyStrategy;

  public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy hystrixConcurrencyStrategy) {
    super();
    this.hystrixConcurrencyStrategy = hystrixConcurrencyStrategy;
  }

  @Override
  public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
    return hystrixConcurrencyStrategy != null ? hystrixConcurrencyStrategy.getBlockingQueue(
        maxQueueSize) : super.getBlockingQueue(maxQueueSize);
  }

  @Override
  public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
    return hystrixConcurrencyStrategy != null ? hystrixConcurrencyStrategy.getRequestVariable(rv)
        : super.getRequestVariable(rv);
  }

  @Override
  public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey,
      HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize,
      HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    return hystrixConcurrencyStrategy != null ? hystrixConcurrencyStrategy.getThreadPool(
        threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
        : super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit,
            workQueue);
  }

  @Override
  public <T> Callable<T> wrapCallable(Callable<T> callable) {
    return hystrixConcurrencyStrategy != null ? hystrixConcurrencyStrategy.wrapCallable(
        new DelegatingUserContextCallable<>(callable, UserContextHolder.getContext()))
        : super.wrapCallable(
            new DelegatingUserContextCallable<>(callable, UserContextHolder.getContext()));
  }
}
