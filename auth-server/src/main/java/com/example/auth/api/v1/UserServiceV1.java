package com.example.auth.api.v1;

import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceV1 {

  private final UserRepository userRepository;

  @HystrixCommand
  @Cacheable(value = "user", key = "#username")
  public Optional<UserEntity> getUserByUsername(String username) {


    return userRepository.findByUsername(username);
  }

}
