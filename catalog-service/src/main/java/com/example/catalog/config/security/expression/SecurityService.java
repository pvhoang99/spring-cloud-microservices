package com.example.catalog.config.security.expression;

public interface SecurityService {

  public boolean hasUser(Long id);

  public boolean isOwn(String createdBy);
}
