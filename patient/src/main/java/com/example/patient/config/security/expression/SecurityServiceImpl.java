package com.example.patient.config.security.expression;

public class SecurityServiceImpl implements SecurityService {


  public boolean hasUser(Long id) {

    //do something
    return true;
  }

  @Override
  public boolean isOwn(String createdBy) {

    return false;
  }
}
