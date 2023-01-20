package com.example.auth.repository;

import com.example.auth.domain.Role;

public interface RoleRepository {

    Role getOne(Long id);

    Role findOne(Long id);
}
