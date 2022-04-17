package com.example.auth.config.security;

import com.example.common.config.CommonResult;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class RedirectLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

  private final Gson gson = new Gson();

  @Setter(onMethod = @__({@Autowired}))
  private ConsumerTokenServices tokenServices;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    PrintWriter out = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    String result;
    CommonResult<?> commonResult;
    String token = request.getParameter("token");
    if (StringUtils.isNotEmpty(token) && tokenServices.revokeToken(token)) {
      commonResult = CommonResult.success("Logout success!");
    } else {
      commonResult = CommonResult.failed("Logout fail");
    }
    result = gson.toJson(commonResult);
    out.print(result);
    out.flush();
  }
}