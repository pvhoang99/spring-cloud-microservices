package com.example.chat.api.v1;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dao.entity.RankedUser;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.service.UserServiceV1;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserControllerV1 {

  private final AuthServiceFeignClient authServiceFeignClient;
  private final UserServiceV1 userServiceV1;

  @GetMapping("/me")
  public ResponseEntity<?> getCurrentUser() {
    return ResponseEntity.ok(authServiceFeignClient.getCurrentUser());
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllUser() {
    return ResponseEntity.ok(userServiceV1.getAll());
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

  @PostMapping(path = "/add-friend/{friendId}")
  public ResponseEntity<?> addFriend(@PathVariable("friendId") Long friendId) {
    userServiceV1.addFriend(friendId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(path = "/remove-friend/{friendId}")
  public ResponseEntity<?> removeFriend(@PathVariable("friendId") Long friendId) {
    userServiceV1.removeFriend(friendId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/get-users-is-not-friend")
  public ResponseEntity<?> getUsersIsNotFriend() {
    return ResponseEntity.ok(userServiceV1.getUsersIsNotFriend());
  }

  @GetMapping(path = "/get-users-is-friend")
  public ResponseEntity<?> getUsersIsFriend() {
    return ResponseEntity.ok(userServiceV1.getUsersIsFriend());
  }

  @GetMapping(path = "/get-users-chat-recently")
  public ResponseEntity<?> getUsersChatRecently() {
    return ResponseEntity.ok(userServiceV1.getUsersChatRecently());
  }
}
