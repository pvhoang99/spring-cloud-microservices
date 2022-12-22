package com.example.auth.repository;

import com.example.auth.domain.user.User;
import java.util.Optional;

public interface UserRepository {

    User getOne(String id);

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean checkExisted(String username);
}
