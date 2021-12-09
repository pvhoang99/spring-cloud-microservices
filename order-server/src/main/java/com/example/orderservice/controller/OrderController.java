package com.example.orderservice.controller;

import com.example.orderservice.client.AuthServiceFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
@AllArgsConstructor
public class OrderController {

  private final AuthServiceFeignClient authServiceFeignClient;

//  private final OrderService orderService;

  @GetMapping
  public Object test() {
    return authServiceFeignClient.testAPI();
  }

  @GetMapping("/me")
  public Object me() {
    return authServiceFeignClient.getCurrentUser();
  }

}
