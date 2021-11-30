package com.example.orderservice.controller;

import com.example.orderservice.client.AuthServiceFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class Controller {

  private final AuthServiceFeignClient authServiceFeignClient;

  public Controller(AuthServiceFeignClient authServiceFeignClient) {
    this.authServiceFeignClient = authServiceFeignClient;
  }

  @GetMapping
  public Object test() {
    return authServiceFeignClient.testAPI();
  }

}
