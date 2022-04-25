package com.example.gatewayserver.falback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

  @GetMapping("/message")
  public ResponseEntity<?> fallback() {
    return ResponseEntity.ok("ERROR!. AN ERROR OCCURRED. PLEASE TRY AGAIN LATER");
  }
}
