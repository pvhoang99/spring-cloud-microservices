package com.example.common.utils;

import java.util.Objects;
import org.springframework.util.Assert;

public class UserContextHolder {

  private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

  public static UserContext getContext() {
    UserContext context = userContext.get();
    if (Objects.isNull(context)) {
      context = createEmptyContext();
      userContext.set(context);
    }

    return userContext.get();
  }

  public static void setContext(UserContext context) {
    Assert.notNull(context, "Only null UserContext instances are permitted");
    userContext.set(context);
  }

  private static UserContext createEmptyContext() {
    return new UserContext();
  }

  public static class UserContext {

    private Long userId;
    private String username;
    private String fullName;
    private String email;

    public UserContext() {

    }

    public UserContext(Long userId, String username, String fullName, String email) {
      this.userId = userId;
      this.username = username;
      this.fullName = fullName;
      this.email = email;
    }

    public Long getUserId() {
      return userId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getFullName() {
      return fullName;
    }

    public void setFullName(String fullName) {
      this.fullName = fullName;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }
  }
}
