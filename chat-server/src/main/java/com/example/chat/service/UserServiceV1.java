package com.example.chat.service;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.dao.repositoty.UserRepository;
import com.example.chat.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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
    userEntity.setId(userDTO.getId());
    return userEntity;
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
}
