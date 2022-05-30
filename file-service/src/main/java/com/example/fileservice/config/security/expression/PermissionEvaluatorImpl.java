package com.example.fileservice.config.security.expression;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

//@PreAuthorize("hasPermission(#id, 'Foo', 'read')")
//@PostAuthorize("hasPermission(returnObject, 'read')")
public class PermissionEvaluatorImpl implements PermissionEvaluator {

  @Override
  public boolean hasPermission(Authentication authentication, Object targetDomainObject,
      Object permission) {
    if (Objects.isNull(authentication) || (Objects.isNull(targetDomainObject)
        || !(permission instanceof String))) {
      return false;
    }
    String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase(Locale.ROOT);
    return hasPrivilege(authentication, targetType, permission.toString().toUpperCase(Locale.ROOT));
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId,
      String targetType, Object permission) {
    if (Objects.isNull(authentication) || !(permission instanceof String)) {
      return false;
    }
    return hasPrivilege(authentication, targetType.toUpperCase(Locale.ROOT),
        permission.toString().toUpperCase(
            Locale.ROOT));
  }

  private boolean hasPrivilege(Authentication authentication, String targetType,
      String permission) {
    for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
      return grantedAuthority.getAuthority().startsWith(targetType)
          && grantedAuthority.getAuthority()
          .contains(permission);
    }
    return false;
  }
}
