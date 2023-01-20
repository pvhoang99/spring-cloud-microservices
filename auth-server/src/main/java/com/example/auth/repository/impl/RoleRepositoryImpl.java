package com.example.auth.repository.impl;

import com.example.auth.domain.Role;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.jpa.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository roleRepository;

    @Override
    public Role getOne(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role findOne(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
