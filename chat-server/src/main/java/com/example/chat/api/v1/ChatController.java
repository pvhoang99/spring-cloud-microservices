package com.example.chat.api.v1;

import com.example.chat.client.AuthServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  AuthServiceFeignClient authServiceFeignClient;

  @GetMapping
  public String string() {
    return "hello";
  }

  @GetMapping("/me")
  public Object me() {
    Object o = SecurityContextHolder.getContext().getAuthentication();
    return authServiceFeignClient.getCurrentUser();
  }

  @GetMapping("/hello")
  public Object hello() {
    Object o = SecurityContextHolder.getContext().getAuthentication();
    return authServiceFeignClient.testAPI();
  }

}
