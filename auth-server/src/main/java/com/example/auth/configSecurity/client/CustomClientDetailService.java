package com.example.auth.configSecurity.client;

import com.example.auth.dao.model.OauthClientDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomClientDetailService implements ClientDetailsService {

  @Autowired
  private ClientDetailRepository clientDetailRepository;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

    OauthClientDetailEntity oauthClientDetailEntity = clientDetailRepository
        .findByClientId(clientId)
        .orElseThrow(() -> new RuntimeException("k tim thay client with client id = " + clientId));

    return new CustomClientDetail(oauthClientDetailEntity);
  }
}
