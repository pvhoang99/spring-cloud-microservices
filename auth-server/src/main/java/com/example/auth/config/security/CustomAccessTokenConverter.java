package com.example.auth.config.security;

import com.example.auth.dao.model.UserEntity;
import com.example.auth.dao.repository.UserRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends JwtAccessTokenConverter {

  @Autowired
  private UserRepository userRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    final Map<String, Object> information = new HashMap<>();
    UserEntity userEntity = userRepository.findByUsername(authentication.getName()).orElse(null);
    /*
    Thêm thông tin vào token
    */
    OAuth2AccessToken oAuth2AccessToken;
    if (userEntity != null) {
      information.put("username", userEntity.getUsername());
      information.put("fullName", userEntity.getFullName());
      information.put("email", userEntity.getEmail());
      information.put("role", userEntity.getRoleEntity().getValue());

    }
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);

    /*
    set thời gian hết hạn của token
    */
    ((DefaultOAuth2AccessToken) accessToken)
        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

    oAuth2AccessToken = super.enhance(accessToken, authentication);
    oAuth2AccessToken.getAdditionalInformation().putAll(information);
    return oAuth2AccessToken;
  }
}
