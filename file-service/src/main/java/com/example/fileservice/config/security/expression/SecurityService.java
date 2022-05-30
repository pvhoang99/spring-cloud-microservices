package com.example.fileservice.config.security.expression;

public interface SecurityService {

  public boolean hasUser(Long id);

  public boolean isOwn(String createdBy);
}
