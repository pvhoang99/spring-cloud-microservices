package com.example.auth.repository;

import com.example.auth.domain.User;
import com.example.auth.domain.user.Username;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User getOne(String id);

    User getByUsername(String username);

    User findByUsername(String username);

    boolean checkExisted(Username username);

}
