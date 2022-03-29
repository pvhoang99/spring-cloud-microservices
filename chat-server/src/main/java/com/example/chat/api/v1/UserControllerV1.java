package com.example.chat.api.v1;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.service.UserServiceV1;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserControllerV1 {

  private final AuthServiceFeignClient authServiceFeignClient;
  private UserServiceV1 userServiceV1;

  @GetMapping
  public Object getAllUser() {
    return userServiceV1.getAll();
  }

  @PostMapping("/sync")
  private ResponseEntity<?> syncUser() {
    userServiceV1.syncUser();
    return ResponseEntity.ok().build();
  }

  @PostMapping()
  public ResponseEntity<?> save(@RequestBody UserEntity userEntity) {
    return ResponseEntity.ok(userServiceV1.save(userEntity));
  }

}
