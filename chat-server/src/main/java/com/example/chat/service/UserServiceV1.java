package com.example.chat.service;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dao.entity.RankedUser;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.dao.repository.UserRepository;
import com.example.chat.dto.UserDTO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceV1 {

  private final AuthServiceFeignClient authServiceFeignClient;
  private final UserRepository userRepository;

  public void syncUser() {
    List<UserDTO> userDTOS = authServiceFeignClient.getAll();
    if (!userDTOS.isEmpty()) {
      userRepository.saveAll(
          userDTOS.stream().filter(e -> !userRepository.existsByUsername(e.getUsername()))
              .map(this::mapToUserEntity).collect(Collectors.toList()));
    }
  }

  private UserEntity mapToUserEntity(UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(userDTO.getUsername());
    userEntity.setEmail(userDTO.getEmail());
    userEntity.setFullName(userDTO.getFullName());
    userEntity.setId(userDTO.getUserId());
    return userEntity;
  }

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

  public UserEntity findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public UserEntity findByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Khong ton tai username: " + username));
  }

  public UserEntity findByUserId(Long userId) {
    return userRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("khong ton tai user with userId: " + userId));
  }

  public Set<UserEntity> findByIds(Set<Long> userIds) {
    return userRepository.findByUserIds(userIds);
  }
}
