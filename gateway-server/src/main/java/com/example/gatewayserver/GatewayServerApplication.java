package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrix
public class GatewayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServerApplication.class, args);
  }

  @Bean
  public InternalLocationRewriteFilter locationRewriteFilter() {
    return new InternalLocationRewriteFilter();
  }

}
