package com.example.auth.repository;

import com.example.auth.domain.role.Role;

public interface RoleRepository {

  Role getOne(String id);

  Role findOne(String id);
}
