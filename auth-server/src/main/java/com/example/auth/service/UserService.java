package com.example.auth.service;

import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public boolean checkUserExisted(String username) {

    return userRepository.findByUsername(username).isPresent();
  }


}
