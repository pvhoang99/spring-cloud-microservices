package com.example.auth.repository.jpa;

import com.example.auth.domain.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByCode(String code);
}
