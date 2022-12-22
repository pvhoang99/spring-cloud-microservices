package com.example.auth.repository.impl;

import com.example.auth.domain.role.Role;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.jpa.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository roleRepository;

    @Override
    public Role getOne(String id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role findOne(String id) {
        return roleRepository.findById(id).orElse(null);
    }
}
