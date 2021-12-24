package com.example.shoppingcartservice.client;

import com.example.shoppingcartservice.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-server")
public interface AuthServiceFeignClient {

  @GetMapping(value = "/v1/me")
  User getCurrentUser();

}
