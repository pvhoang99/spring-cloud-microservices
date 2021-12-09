package com.example.auth.api.v1;

import com.example.auth.dao.model.UserEntity;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserControllerV1 {

  private final UserServiceV1 userService;

  @RequestMapping(path = "/me")
  @PreAuthorize("#oauth2.hasScope('server')")
  public ResponseEntity<UserEntity> me(Principal principal) {
    UserEntity user = null;
    if (principal != null) {
      user = userService.getUserByUsername(principal.getName()).orElse(null);
    }
    return Optional.ofNullable(user)
        .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
  }

}
