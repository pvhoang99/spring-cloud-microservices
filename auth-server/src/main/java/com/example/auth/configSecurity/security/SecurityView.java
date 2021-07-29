package com.example.auth.configSecurity.security;

import com.example.auth.configSecurity.security.Views.Role;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@RestControllerAdvice(basePackages = { "com.example.auth" })
public class SecurityView extends AbstractMappingJacksonResponseBodyAdvice {

  @Override
  protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
      MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
    if (SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {

      Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
          .getAuthentication()
          .getAuthorities();

      List<Class> jsonViews = authorities.stream()
          .map(GrantedAuthority::getAuthority).map(Role::valueOf)
          .map(Views.MAPPING::get)
          .collect(Collectors.toList());
      if (jsonViews.size() == 1)
        bodyContainer.setSerializationView(jsonViews.get(0));
      else
        throw new IllegalArgumentException("Ambiguous declaration for roles " + authorities.stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
    }
  }
}
