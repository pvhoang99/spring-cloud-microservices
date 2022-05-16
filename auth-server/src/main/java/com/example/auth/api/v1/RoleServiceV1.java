package com.example.auth.api.v1;

import com.example.auth.dao.model.RoleEntity;
import com.example.auth.dao.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceV1 {

  private final RoleRepository roleRepository;

  public List<RoleEntity> getAll() {
    return roleRepository.findAll(Sort.by("name"));
  }
  public RoleEntity save(RoleEntity roleEntity) {
    return roleRepository.save(roleEntity);
  }

  public RoleEntity findByValue(String value) {
    return roleRepository.findByValue(value)
        .orElseThrow(() -> new RuntimeException("not exist roleEntity with value: " + value));
  }
}
