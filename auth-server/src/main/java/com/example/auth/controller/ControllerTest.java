package com.example.auth.controller;

import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerTest {

  @GetMapping(value = "/hello", produces = "application/JSON")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> hello() {
    Map map = new HashMap();
    map.put("key", "hoang");
    return ResponseEntity.ok(map);
  }

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(path = "/me")
  public ResponseEntity me(Principal principal) {
    UserEntity user = null;
    if (principal != null) {
      user = userRepository.findByUsername(principal.getName()).get();
    }

    return Optional.ofNullable(user)
        .map(a -> new ResponseEntity<UserEntity>(a, HttpStatus.OK))
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
  }

  //Đoạn này tạm thời fix cứng dữ liệu ahihi
  @RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> user(Principal user) {
    Map map = new HashMap();
    map.put("id", "hoang1");
    map.put("name", "Phạm Việt Hoàng");
    map.put("email", "PhamVietHoang@gmail.com");
    map.put("picture", "ahihi");
    return map;
  }

}