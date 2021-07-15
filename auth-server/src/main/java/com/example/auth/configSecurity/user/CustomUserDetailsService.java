package com.example.auth.configSecurity.user;

import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("k ton tai" + username));

    return new CustomUserDetail(userEntity);
  }
}
