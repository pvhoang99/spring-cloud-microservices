package com.example.auth.api.v1;

import com.example.auth.dao.repository.UserRepository;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerTest {

  @GetMapping(value = "/hello")
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
  public ResponseEntity<?> me(Principal principal) {
    return ResponseEntity.ok(principal);
  }

  //Đoạn này tạm thời fix cứng dữ liệu
  @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> user() {
    Map map = new HashMap();
    map.put("id", "hoang1");
    map.put("name", "Phạm Việt Hoàng");
    map.put("email", "PhamVietHoang@gmail.com");
    map.put("picture", "");
    return map;
  }

}