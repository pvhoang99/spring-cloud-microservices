package com.example.chat.api.v1;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dao.entity.RankedUser;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.service.UserServiceV1;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserControllerV1 {

  private final AuthServiceFeignClient authServiceFeignClient;
  private final UserServiceV1 userServiceV1;

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

  @GetMapping(path = "/findMutualFriends/{friendId}")
  public Flux<UserEntity> getMutualFriends(@PathVariable("friendId") Long friendId) {
    return Flux.fromIterable(userServiceV1.mutualFriends(friendId));
  }

  @GetMapping(path = "/recommend-friends")
  public Flux<RankedUser> getRecommendFriends() {
    return Flux.fromIterable(userServiceV1.recommendFriend());
  }

}
