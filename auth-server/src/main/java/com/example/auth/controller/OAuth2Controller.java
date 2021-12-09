package com.example.auth.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/oauth")
@SessionAttributes({"authorizationRequest",
    "org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.ORIGINAL_AUTHORIZATION_REQUEST"})
public class OAuth2Controller {

  @Autowired
  private WhitelabelErrorEndpoint whitelabelErrorEndpoint;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private AuthorizationEndpoint authorizationEndpoint;

  @RequestMapping(value = "/authorize", method = RequestMethod.GET)
  public ModelAndView authorize(Map<String, Object> model,
      @RequestParam Map<String, String> parameters, SessionStatus sessionStatus,
      HttpSession session) {
    Authentication authentication = (Authentication) session.getAttribute("authentication");
    if (authentication == null || !authentication.isAuthenticated()) {
      session.setAttribute("client_id", parameters.getOrDefault("client_id", null));
      session.setAttribute("response_type", parameters.getOrDefault("response_type", null));
      session.setAttribute("redirect_uri", parameters.getOrDefault("redirect_uri", null));
      session.setAttribute("scope", parameters.getOrDefault("scope", null));
      session.setAttribute("state", parameters.getOrDefault("state", null));
      return new ModelAndView("redirect:/login");
    }
    return authorizationEndpoint.authorize(model, parameters, sessionStatus, authentication);
  }

  @RequestMapping(
      value = {"/authorize"},
      method = {RequestMethod.POST},
      params = {"user_oauth_approval"}
  )
  public View approveOrDeny(@RequestParam Map<String, String> approvalParameters,
      Map<String, ?> model, SessionStatus sessionStatus,
      HttpSession session) {
    Authentication authentication = (Authentication) session.getAttribute("authentication");
    return authorizationEndpoint
        .approveOrDeny(approvalParameters, model, sessionStatus, authentication);
  }

  @RequestMapping("/error")
  public ModelAndView customErrorPage(HttpServletRequest request) {
    // TODO: custom code here
    return whitelabelErrorEndpoint.handleError(request);
  }

  @DeleteMapping("/revoke_token")
  public ResponseEntity<?> revokeToken(@RequestParam Map<String, Object> requestParam) {
    String accessToken = (String) requestParam.get("access_token");
    String refreshToken = (String) requestParam.get("refresh_token");
    OAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
    OAuth2RefreshToken oAuth2RefreshToken = new DefaultOAuth2RefreshToken(refreshToken);
    tokenStore.removeAccessToken(oAuth2AccessToken);
    tokenStore.removeRefreshToken(oAuth2RefreshToken);
    Map<String, Object> response = new HashMap<>();
    response.put("message", "success");
    return ResponseEntity.ok(response);
  }
}