package com.example.chat.service;

import com.example.chat.dao.entity.RankedUser;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.dao.repository.UserRepository;
import com.example.chat.dto.LoginByUsernameAndPasswordDTO;
import com.example.grpc.auth.AuthServiceGrpc;
import com.example.grpc.auth.LoginRequest;
import com.example.grpc.auth.LoginResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceV1 {

  private final UserRepository userRepository;

  @GrpcClient("auth-server")
  private AuthServiceGrpc.AuthServiceBlockingStub serviceBlockingStub;

  public Streamable<UserEntity> mutualFriends(Long friendId) {

    UserEntity currentUser = this.getCurrentUser();
    if (!userRepository.existsByUserId(friendId)) {
      throw new RuntimeException("Khong ton tai userId: " + friendId);
    }
    return userRepository.mutualFriend(currentUser.getUserId(), friendId);
  }

  public Streamable<RankedUser> recommendFriend() {
    return userRepository.recommendedFriends(this.getCurrentUser().getUserId());
  }

  public UserEntity getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = (String) authentication.getPrincipal();

    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("user not exist with: " + username));
  }

  public Iterable<UserEntity> getAll() {
    return userRepository.findAll();
  }

  public UserEntity save(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  public UserEntity findByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Khong ton tai username: " + username));
  }

  public UserEntity findByUserId(Long userId) {
    return userRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("khong ton tai user with userId: " + userId));
  }

  public void addFriend(Long friendId) {
    UserEntity userEntity = this.getCurrentUser();
    userRepository.addFriend(userEntity.getUserId(), friendId, new Date());
  }

  public void removeFriend(Long friendId) {
    UserEntity userEntity = this.getCurrentUser();
    userRepository.removeFriend(userEntity.getUserId(), friendId);
  }

  public List<UserEntity> getUsersIsNotFriend() {
    UserEntity userEntity = this.getCurrentUser();
    return userRepository.getUserIsNotFriend(userEntity.getUserId()).toList();
  }

  public List<UserEntity> getUsersIsFriend() {
    UserEntity userEntity = this.getCurrentUser();
    return userRepository.getUserIsFriend(userEntity.getUserId()).toList();
  }

  public List<UserEntity> getUsersChatRecently() {
    UserEntity userEntity = this.getCurrentUser();
    return userRepository.getUsersChatRecently(userEntity.getUserId()).toList();
  }

  public Map<String, Object> loginResponse(LoginByUsernameAndPasswordDTO login) {
    LoginResponse loginResponse = serviceBlockingStub.login(
        LoginRequest.newBuilder()
            .setUsername(login.getUsername())
            .setPassword(login.getPassword())
            .setClientId("chat-server")
            .setGrantType("grant_type")
            .setClientSecret("1")
            .build());
    return new HashMap<>() {{
      put("token", loginResponse.getAccessToken());
    }};
  }
}
