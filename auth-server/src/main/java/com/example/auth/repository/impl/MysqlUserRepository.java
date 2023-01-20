package com.example.auth.repository.impl;

import com.example.auth.domain.User;
import com.example.auth.domain.user.Username;
import com.example.auth.repository.UserRepository;
import com.example.auth.repository.jpa.JpaUserRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MysqlUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        return this.jpaUserRepository.save(user);
    }

    @Override
    public User getOne(String id) {
        return this.jpaUserRepository.getOne(id);
    }

    @Override
    public User getByUsername(String username) {
        return this.jpaUserRepository.findByCredentials_Username(Username.create(username)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByUsername(String username) {
        return this.jpaUserRepository.findByCredentials_Username(Username.create(username)).orElse(null);
    }

    @Override
    public boolean checkExisted(Username username) {
        return this.jpaUserRepository.existsByCredentialsUsername(username);
    }

}
