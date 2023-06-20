package com.example.auth.service;

import com.example.auth.domain.user.Username;
import com.example.auth.repository.UserRepository;
import com.example.common.exception.UserAlreadyExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void checkUserExisted(String username) {
    boolean isUserExisted = userRepository.checkExisted(Username.create(username));
    if (isUserExisted) {
      throw new UserAlreadyExistedException(username);
    }
  }

}
