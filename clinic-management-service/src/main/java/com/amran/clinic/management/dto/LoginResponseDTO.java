package com.amran.clinic.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginResponseDTO {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("scope")
  private String scope;

  @JsonProperty("jti")
  private String jti;

  @JsonProperty("user_role")
  private String userRole;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("userName")
  private String username;

  @JsonProperty("email")
  private String email;

}
