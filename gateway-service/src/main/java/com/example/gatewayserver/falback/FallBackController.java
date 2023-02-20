package com.example.gatewayserver.falback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

  @GetMapping("/message")
  public Mono<ResponseEntity<?>> fallback() {
    return Mono.just(ResponseEntity.ok("ERROR!. AN ERROR OCCURRED. PLEASE TRY AGAIN LATER"));
  }
}
