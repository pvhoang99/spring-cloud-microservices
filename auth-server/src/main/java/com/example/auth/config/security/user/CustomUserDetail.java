package com.example.auth.config.security.user;

import com.example.auth.dao.model.RoleEntity;
import com.example.auth.dao.model.UserEntity;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {

  private UserEntity userEntity;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    RoleEntity roleEntity = userEntity.getRoleEntity();
    if (roleEntity != null) {
      return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleEntity.getValue()));
    }
    return null;
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public String getUsername() {
    return userEntity.getUsername();
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
    return userEntity.getIsActive();
  }
}
