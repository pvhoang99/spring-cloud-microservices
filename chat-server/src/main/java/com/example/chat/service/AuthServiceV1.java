package com.example.chat.service;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dto.LoginByCodeDTO;
import com.example.chat.dto.LoginByUsernameAndPasswordDTO;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 {

  public final AuthServiceFeignClient authServiceFeignClient;

  public Map<String, Object> loginByCode(LoginByCodeDTO loginByCodeDTO) {

    Map<String, String> parameters = new HashMap<>();
    parameters.put("client_id", "hoang");
    parameters.put("client_secret", "1");
    parameters.put("grant_type", "authorization_code");
    parameters.put("code", loginByCodeDTO.getCode());
    parameters.put("state", loginByCodeDTO.getState());
    parameters.put("redirect_uri", loginByCodeDTO.getRedirectURI());

    return authServiceFeignClient.login(parameters);

  }

  public Map<String, Object> loginByUsernameAndPassword(LoginByUsernameAndPasswordDTO loginDTO) {

    Map<String, String> parameters = new HashMap<>();
    parameters.put("client_id", "hoang");
    parameters.put("client_secret", "1");
    parameters.put("grant_type", "password");
    parameters.put("username", loginDTO.getUsername());
    parameters.put("password", loginDTO.getPassword());
    return authServiceFeignClient.login(parameters);
  }

}
