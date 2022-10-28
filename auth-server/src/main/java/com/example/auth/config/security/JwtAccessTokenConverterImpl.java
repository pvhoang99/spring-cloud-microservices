package com.example.auth.config.security;

import com.example.auth.domain.role.Role;
import com.example.auth.domain.user.User;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessTokenConverterImpl extends JwtAccessTokenConverter {

  @Setter(onMethod = @__({@Autowired}))
  private UserRepository userRepository;
  @Setter(onMethod = @__({@Autowired}))
  private RoleRepository roleRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    final Map<String, Object> information = new HashMap<>();
    User user = userRepository.getByUsername(authentication.getName());
    Role role = null;
    if (ObjectUtils.isNotEmpty(user.getRoleId())) {
      role = roleRepository.getOne(user.getRoleId());
    }

    information.put("username", user.getUsername());
    information.put("fullName", user.getFullName());
    information.put("email", user.getEmail());
    if (ObjectUtils.isNotEmpty(role)) {
      information.put("role", role.getValue());
    }

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
    ((DefaultOAuth2AccessToken) accessToken).setExpiration(Date.valueOf(LocalDate.now().plusDays(10)));

    return super.enhance(accessToken, authentication);
  }
}
