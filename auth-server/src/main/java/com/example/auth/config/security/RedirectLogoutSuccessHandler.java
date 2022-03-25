package com.example.auth.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class RedirectLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    String uri = request.getParameter("redirect_uri");

    if (StringUtils.isNotEmpty(uri)) {
      getRedirectStrategy().sendRedirect(request, response, uri);
      response.setStatus(HttpServletResponse.SC_OK);
    }

  }
}