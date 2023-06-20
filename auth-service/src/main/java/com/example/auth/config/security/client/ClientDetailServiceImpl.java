package com.example.auth.config.security.client;

import com.example.auth.domain.authentication.OauthClientDetailEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
public class ClientDetailServiceImpl implements ClientDetailsService {

  private final ClientDetailRepository clientDetailRepository;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    OauthClientDetailEntity oauthClientDetailEntity = clientDetailRepository.findByClientId(
            clientId)
        .orElseThrow(() -> new RuntimeException("k tim thay client with client id = " + clientId));

    return new ClientDetailImpl(oauthClientDetailEntity);
  }
}
