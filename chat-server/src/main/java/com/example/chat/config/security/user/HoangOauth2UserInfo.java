package com.example.chat.config.security.user;

import java.util.Map;

public class HoangOauth2UserInfo extends OAuth2UserInfo {

  public HoangOauth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
  }

  @Override
  public String getId() {
    return String.valueOf(attributes.get("userId"));
  }

  @Override
  public String getName() {
    return (String) attributes.get("fullName");
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }

  @Override
  public String getImageUrl() {
    //Todo: cần thêm imageUrl
    return null;

  }

  @Override
  public String getUsername() {
    return (String) attributes.get("username");
  }
}
