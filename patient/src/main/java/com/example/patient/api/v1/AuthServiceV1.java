package com.example.patient.api.v1;

import com.example.grpc.auth.AuthServiceGrpc;
import com.example.grpc.auth.LoginRequest;
import com.example.grpc.auth.LoginResponse;
import com.example.patient.dto.LoginByUsernameAndPasswordDTO;
import java.util.HashMap;
import java.util.Map;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceV1 {

  @GrpcClient("auth-server")
  private AuthServiceGrpc.AuthServiceBlockingStub serviceBlockingStub;

  public Map<String, Object> loginResponse(LoginByUsernameAndPasswordDTO login) {
    LoginResponse loginResponse = serviceBlockingStub.login(
        LoginRequest.newBuilder()
            .setUsername(login.getUsername())
            .setPassword(login.getPassword())
            .setClientId("hoang")
            .setGrantType("password")
            .setClientSecret("1")
            .build());
    return new HashMap<>() {
      {
        put("access_token", loginResponse.getAccessToken());
        put("refresh_token", loginResponse.getRefreshToken());
      }
    };
  }

}
