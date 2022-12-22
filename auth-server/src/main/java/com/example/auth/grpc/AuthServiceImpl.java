package com.example.auth.grpc;

import com.example.grpc.auth.AuthServiceGrpc;
import com.example.grpc.auth.LoginRequest;
import com.example.grpc.auth.LoginResponse;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;

@GrpcService
@RequiredArgsConstructor
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    private final TokenEndpoint tokenEndpoint;

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
