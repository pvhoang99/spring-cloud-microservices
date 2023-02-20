package com.example.auth.config.security;

import com.example.auth.domain.Role;
import com.example.auth.domain.User;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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

    private static final String USER_ID_KEY = "userId";
    private static final String USERNAME_KEY = "username";
    private static final String FULL_NAME_KEY = "fullName";
    private static final String EMAIL_KEY = "email";
    private static final String ROLE_KEY = "role";
    private static final Date EXPIRATION = Date.valueOf(LocalDate.now().plusDays(10));

    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Setter(onMethod = @__({@Autowired}))
    private RoleRepository roleRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> information = this.buildInformation(authentication);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
        ((DefaultOAuth2AccessToken) accessToken).setExpiration(EXPIRATION);

        return super.enhance(accessToken, authentication);
    }

    private Map<String, Object> buildInformation(OAuth2Authentication authentication) {
        Map<String, Object> information = new HashMap<>();
        User user = userRepository.getByUsername(authentication.getName());
        Role role = null;
        if (ObjectUtils.isNotEmpty(user.getRoleId())) {
            role = roleRepository.getOne(user.getRoleId());
        }
        information.put(USER_ID_KEY, user.getId());
        information.put(USERNAME_KEY, user.getUsername());
        information.put(FULL_NAME_KEY, user.getFullName());
        information.put(EMAIL_KEY, user.getEmail());
        if (ObjectUtils.isNotEmpty(role)) {
            information.put(ROLE_KEY, role.getCode());
        }

        return information;
    }


}
