package com.example.auth.config.security;

import java.util.HashMap;
import java.util.Map;

public class Views {

  public static final Map<Role, Class> MAPPING = new HashMap<Role, Class>() {
    {
      put(Role.ROLE_USER, User.class);
      put(Role.ROLE_ADMIN, Admin.class);
    }
  };

  public enum Role {
    ROLE_ADMIN, ROLE_USER
  }

  public static interface User {

  }

  public static interface Admin extends User {

  }

}
