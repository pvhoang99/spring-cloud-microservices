package com.example.auth.config.security;

import com.example.common.config.CommonResult;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class RedirectLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

  private final Gson gson = new Gson();

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    CommonResult<?> commonResult = CommonResult.success("Logout success!d");
    String result = gson.toJson(commonResult);
    PrintWriter out = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    out.print(result);
    out.flush();

  }
}