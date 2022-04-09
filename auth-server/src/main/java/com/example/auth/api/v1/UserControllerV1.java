package com.example.auth.api.v1;

import com.example.auth.dao.model.UserEntity;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

  private final UserServiceV1 userService;

  @RequestMapping(path = "/me", method = RequestMethod.GET)
  @PreAuthorize("hasRole('USER') OR #oauth2.hasScope('server')")
  public ResponseEntity<UserEntity> me(Principal principal) {
    UserEntity user = null;
    if (principal != null) {
      user = userService.getUserByUsername(principal.getName()).orElse(null);
    }
    return Optional.ofNullable(user)
        .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
  }

  @PostMapping()
  public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity) {

    return ResponseEntity.ok(userService.saveUser(userEntity));
  }

  @GetMapping("/get-all")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
}
