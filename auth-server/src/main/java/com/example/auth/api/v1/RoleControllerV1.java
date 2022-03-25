package com.example.auth.api.v1;

import com.example.auth.dao.model.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleControllerV1 {

  private final RoleServiceV1 roleServiceV1;

  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(roleServiceV1.getAll());
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody RoleEntity roleEntity) {
    return ResponseEntity.ok(roleServiceV1.save(roleEntity));
  }

}
