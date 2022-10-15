package com.example.auth.api.v1;

import com.example.auth.domain.role.Role;
import com.example.auth.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceV1 {

  private final RoleRepository roleRepository;

  public List<Role> getAll() {
    return roleRepository.findAll(Sort.by("name"));
  }
  public Role save(Role role) {
    return roleRepository.save(role);
  }

  public Role findByValue(String value) {
    return roleRepository.findByValue(value)
        .orElseThrow(() -> new RuntimeException("not exist roleEntity with value: " + value));
  }
}
