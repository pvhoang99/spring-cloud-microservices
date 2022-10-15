package com.example.auth.config.security;

import com.example.auth.domain.user.User;
import com.example.auth.repository.UserRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessTokenConverter extends JwtAccessTokenConverter {

  @Setter(onMethod = @__({@Autowired}))
  private UserRepository userRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    final Map<String, Object> information = new HashMap<>();
    User user = userRepository.findByUsername(authentication.getName()).orElse(null);
    /*
    Thêm thông tin vào token
    */
    if (user != null) {
      information.put("username", user.getUsername());
      information.put("fullName", user.getFullName());
      information.put("email", user.getEmail());
      information.put("role", user.getRole().getValue());

    }
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);

    /*
    set thời gian hết hạn của token
    */
    ((DefaultOAuth2AccessToken) accessToken)
        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(10)));
    return super.enhance(accessToken, authentication);
  }
}
