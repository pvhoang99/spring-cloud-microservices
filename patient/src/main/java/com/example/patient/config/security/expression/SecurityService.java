package com.example.patient.config.security.expression;

public interface SecurityService {

  public boolean hasUser(Long id);

  public boolean isOwn(String createdBy);
}
