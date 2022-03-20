package com.example.auth.controller;

import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  @RequestMapping("/")
  @ResponseBody
  public String home() {
    return "hello";
  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String login(HttpServletRequest request, Authentication authentication,
      Model model, @CookieValue(value = "username", defaultValue = "") String usernameCookie) {

    if (authentication != null && authentication.isAuthenticated()) {
      return "redirect:/";
    }

    HttpSession session = request.getSession();

    if (session == null) {
      return "login";
    }

    if (session.getAttribute("redirect_uri") == null) {
      return "redirect:/";
    }

    String paras = request.getQueryString();
    if (paras != null && paras.startsWith("error")) {
      Boolean onAuthenticationFailure = (Boolean) session.getAttribute("onAuthenticationFailure");

      if (onAuthenticationFailure == null || !onAuthenticationFailure) {
        model.addAttribute("error", true);
        return "login";
      }

      String password = (String) session.getAttribute("password");
      String username = (String) session.getAttribute("username");

      model.addAttribute("password", password == null ? "" : password);
      model.addAttribute("username", username == null ? "" : username);
    } else {
      model.addAttribute("username",
          usernameCookie == null ? "" : new String(Base64.getDecoder().decode(usernameCookie)));
    }
    session.setAttribute("onAuthenticationFailure", false);
    session.setAttribute("password", null);
    return "login";
  }

}
