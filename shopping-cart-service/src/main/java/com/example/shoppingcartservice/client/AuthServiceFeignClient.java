package com.example.shoppingcartservice.client;


import com.example.shoppingcartservice.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-server")
public interface AuthServiceFeignClient {

  @GetMapping(value = "/auth-server/api/v1/user/me")
  User getCurrentUser();

}
