package com.example.auth.config.security.client;

import com.example.auth.domain.authentication.OauthClientDetailEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailRepository extends JpaRepository<OauthClientDetailEntity, String> {

  Optional<OauthClientDetailEntity> findByClientId(String clientId);

  boolean existsByClientId(String clientId);
}
