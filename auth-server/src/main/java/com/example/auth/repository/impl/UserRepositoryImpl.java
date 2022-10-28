package com.example.auth.repository.impl;

import com.example.auth.domain.user.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.repository.jpa.JpaUserRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  @Override
  public User getOne(String id) {
    return jpaUserRepository.getOne(id);
  }

  @Override
  public User getByUsername(String username) {
    return jpaUserRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return jpaUserRepository.findByUsername(username);
  }
}
