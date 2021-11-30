package com.example.auth.configSecurity.security;

import com.example.auth.configSecurity.client.ClientDetailRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class MyAuthenticationHandler {

  @Autowired
  ClientDetailRepository clientDetailRepository;

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return (request, response, authentication) -> {
      HttpSession session = request.getSession();
      String clientId = (String) session.getAttribute("client_id");
      String responseType = (String) session.getAttribute("response_type");
      String redirectUri = (String) session.getAttribute("redirect_uri");
      String scope = (String) session.getAttribute("scope");
      if (scope == null) {
        scope = "";
      }
      String state = (String) session.getAttribute("state");
      if (state == null) {
        state = "";
      }
      session.setAttribute("authentication", authentication);
      session.removeAttribute("client_id");
      session.removeAttribute("response_type");
      session.removeAttribute("redirect_uri");
      session.removeAttribute("scope");
      session.removeAttribute("state");
      response.sendRedirect(
          "/oauth/authorize?client_id=" + clientId + "&response_type=" + responseType +
              "&redirect_uri=" + redirectUri + "&scope=" + scope + "&state=" + state);
    };
  }
}