package com.example.shoppingcartservice.user;

import java.io.Serializable;

/**
 * @author chaokunyang
 */
public class User implements Serializable {

  private Long userId;
  private String username;
  private String fullName;
  private String email;

  public User() {
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

  @Override
  public String toString() {
    return "User{" +
        "userId" + userId +
        ", username='" + username + '\'' +
        ", fullName='" + fullName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}