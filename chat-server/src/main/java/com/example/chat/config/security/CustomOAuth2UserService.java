package com.example.chat.config.security;


import com.example.chat.config.security.exception.OAuth2AuthenticationProcessingException;
import com.example.chat.config.security.user.OAuth2UserInfo;
import com.example.chat.config.security.user.OAuth2UserInfoFactory;
import com.example.chat.config.security.user.OAuth2UserInfoFactory.AuthProvider;
import com.example.chat.config.security.user.UserPrincipal;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.dao.repositoty.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
      throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    try {
      return processOAuth2User(oAuth2UserRequest, oAuth2User);
    } catch (AuthenticationException ex) {
      throw ex;
    } catch (Exception ex) {
      // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
    }
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
        .getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
            oAuth2User.getAttributes());
    if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
    }

    Optional<UserEntity> userOptional = userRepository.findByUsername(oAuth2UserInfo.getUsername());
    UserEntity user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      if (!user.getProvider().equals(
          AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
        throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
            user.getProvider() + " account. Please use your " + user.getProvider() +
            " account to login.");
      }
      user = updateExistingUser(user, oAuth2UserInfo, oAuth2UserRequest);
    } else {
      user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
    }

    return UserPrincipal.create(user, oAuth2User.getAttributes());
  }

  private UserEntity registerNewUser(OAuth2UserRequest oAuth2UserRequest,
      OAuth2UserInfo oAuth2UserInfo) {
    UserEntity user = new UserEntity();
    user.setProvider(
        AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setProviderId(oAuth2UserInfo.getId());
    user.setFullName(oAuth2UserInfo.getName());
    user.setEmail(oAuth2UserInfo.getEmail());
    user.setImageUrl(oAuth2UserInfo.getImageUrl());
    user.setToken(oAuth2UserRequest.getAccessToken().getTokenValue());
    return userRepository.save(user);
  }

  private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oAuth2UserInfo,
      OAuth2UserRequest oAuth2UserRequest) {

    existingUser.setFullName(oAuth2UserInfo.getName());
    existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
    existingUser.setToken(oAuth2UserRequest.getAccessToken().getTokenValue());
    return userRepository.save(existingUser);
  }

}
