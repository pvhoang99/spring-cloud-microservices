package com.example.auth.dao.repository;

import com.example.auth.dao.model.UserEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String name);

  @Query(value = "select e from UserEntity e")
  Page<UserEntity> search(Pageable pageable);

  boolean existsByUsername(String username);
}
