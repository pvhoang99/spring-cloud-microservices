package com.example.auth.controller;

import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(path = "/me")
  public ResponseEntity me(Principal principal) {
    UserEntity user = null;
    if(principal != null) {
      user = userRepository.findByUsername(principal.getName()).get();
    }

    return Optional.ofNullable(user)
        .map(a -> new ResponseEntity<UserEntity>(a, HttpStatus.OK))
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
  }

  //Đoạn này tạm thời fix cứng dữ liệu ahihi
  @RequestMapping("/user")
  public Map user(Principal user) {
    Map map = new HashMap();
    map.put("id","hoang1");
    map.put("name","Phạm Việt Hoàng");
    map.put("email","PhamVietHoang@gmail.com");
    map.put("picture","ahihi");
    return map;
  }
}
