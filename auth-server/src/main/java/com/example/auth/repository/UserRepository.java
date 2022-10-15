package com.example.auth.repository;

import com.example.auth.domain.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String name);

  @Query(value = "select e from User e")
  Page<User> search(Pageable pageable);

  boolean existsByUsername(String username);
}
