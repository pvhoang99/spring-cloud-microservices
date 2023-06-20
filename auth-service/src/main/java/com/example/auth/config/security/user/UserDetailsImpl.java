package com.example.auth.config.security.user;

import com.example.auth.domain.Role;
import com.example.auth.domain.User;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private final User user;
  private final Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role != null) {
      return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
    }
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_AUTHENTICATED"));
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.user.isActive();
  }

}
