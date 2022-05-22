package com.example.auth.api.v1;

import com.example.auth.dao.model.RoleEntity;
import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceV1 {

  private final UserRepository userRepository;
  private final RoleServiceV1 roleServiceV1;

  @HystrixCommand(fallbackMethod = "getUserByUsernameFallBack")
  @Cacheable(value = "user", key = "#username")
  public Optional<UserEntity> getUserByUsername(String username) {

    return userRepository.findByUsername(username);
  }

  public Optional<UserEntity> getUserByUsernameFallBack(String username) {
    return Optional.empty();
  }

  public UserEntity saveUser(UserEntity userEntity) {

    if (userRepository.existsByUsername(userEntity.getUsername())) {
      throw new RuntimeException("user is exist with: " + userEntity.getUsername());
    }
    //hardcode
    RoleEntity roleEntity = roleServiceV1.findByValue("USER");
    userEntity.setRoleId(roleEntity.getId());
    userEntity.setRoleEntity(roleEntity);
    return userRepository.save(userEntity);

  }

  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  public Page<UserEntity> search(Pageable pageable) {
    return userRepository.search(pageable);
  }
}
