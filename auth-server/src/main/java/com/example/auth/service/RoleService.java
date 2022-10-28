package com.example.auth.service;

import com.example.auth.repository.jpa.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final JpaRoleRepository jpaRoleRepository;


}
