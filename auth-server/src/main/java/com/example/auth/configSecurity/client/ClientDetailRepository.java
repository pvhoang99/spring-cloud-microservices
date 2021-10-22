package com.example.auth.configSecurity.client;

import com.example.auth.dao.model.OauthClientDetailEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailRepository extends JpaRepository<OauthClientDetailEntity, String> {

  Optional<OauthClientDetailEntity> findByClientId(String clientId);
}
