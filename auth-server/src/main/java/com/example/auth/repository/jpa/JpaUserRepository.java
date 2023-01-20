package com.example.auth.repository.jpa;

import com.example.auth.domain.User;
import com.example.auth.domain.user.Username;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, String> {

    Optional<User> findByCredentials_Username(Username username);

    @Query(value = "select e from User e")
    Page<User> search(Pageable pageable);

    boolean existsByCredentialsUsername(Username username);
}
