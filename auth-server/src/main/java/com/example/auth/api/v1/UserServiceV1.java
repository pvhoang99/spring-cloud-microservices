package com.example.auth.api.v1;

import com.example.auth.domain.role.Role;
import com.example.auth.domain.user.User;
import com.example.auth.repository.UserRepository;
import com.example.grpc.auth.AuthServiceGrpc;
import com.example.grpc.auth.LoginRequest;
import com.example.grpc.auth.LoginResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;

@RequiredArgsConstructor
@GrpcService
public class UserServiceV1 extends AuthServiceGrpc.AuthServiceImplBase {

  private final UserRepository userRepository;
  private final RoleServiceV1 roleServiceV1;
  private final TokenEndpoint tokenEndpoint;

  @HystrixCommand(fallbackMethod = "getUserByUsernameFallBack")
  @Cacheable(value = "user", key = "#username")
  public Optional<User> getUserByUsername(String username) {

    return userRepository.findByUsername(username);
  }

  public Optional<User> getUserByUsernameFallBack(String username) {
    return Optional.empty();
  }

  public User saveUser(User user) {

    if (userRepository.existsByUsername(user.getUsername())) {
      throw new RuntimeException("user is exist with: " + user.getUsername());
    }
    //hardcode
    Role role = roleServiceV1.findByValue("USER");
    user.setRole(role);
    return userRepository.save(user);

  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Page<User> search(Pageable pageable) {
    return userRepository.search(pageable);
  }

  @Override
  public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
    try {
      Map<String, String> parameters = new LinkedHashMap<String, String>() {{
        put("username", request.getUsername());
        put("password", request.getPassword());
        put("grant_type", request.getGrantType());
        put("client_id", request.getClientId());
        put("client_secret", request.getClientSecret());
      }};
      ResponseEntity<OAuth2AccessToken> response = tokenEndpoint.postAccessToken(
          new UsernamePasswordAuthenticationToken(request.getClientId(), null, new ArrayList<>()),
          parameters);
      OAuth2AccessToken accessToken = response.getBody();
      assert accessToken != null;
      LoginResponse loginResponse = LoginResponse.newBuilder()
          .setAccessToken(accessToken.getValue())
          .setRefreshToken(accessToken.getRefreshToken().getValue()).build();
      responseObserver.onNext(loginResponse);
      responseObserver.onCompleted();
    } catch (Exception e) {
      responseObserver.onError(new RuntimeException("error login"));
    }
  }
}
