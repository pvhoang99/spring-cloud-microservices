package com.example.auth.repository.jpa;

import com.example.auth.domain.role.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, String> {

  Optional<Role> findByValue(String value);
}
