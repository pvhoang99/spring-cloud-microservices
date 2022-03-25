package com.example.chat.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-server")
public interface AuthServiceFeignClient {

  @GetMapping(value = "/auth-server/api/hello")
  Object testAPI();

  @GetMapping(value = "/auth-server/api/v1/user/me")
  Object getCurrentUser();

}
