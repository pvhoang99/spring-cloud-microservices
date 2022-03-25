package com.example.auth.config.security.client;

import com.example.auth.dao.model.OauthClientDetailEntity;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@AllArgsConstructor
@Data
public class CustomClientDetail implements ClientDetails {

  private OauthClientDetailEntity client;

  @Override
  public String getClientId() {
    return client.getClientId();
  }

  @Override
  public Set<String> getResourceIds() {

    String resourceList = client.getResourceIds();

    if (resourceList != null) {

      String[] resourceIds = resourceList.split(",");

      Sets.newHashSet(resourceIds);

      return Sets.newHashSet(resourceIds);
    }
    return null;
  }

  @Override
  public boolean isSecretRequired() {
    return true;
  }

  @Override
  public String getClientSecret() {
    return client.getClientSecret();
  }

  @Override
  public boolean isScoped() {
    return true;
  }

  @Override
  public Set<String> getScope() {

    String scopes = client.getScope();

    if (scopes != null) {

      String[] arrayScope = scopes.split(",");

      return Sets.newHashSet(arrayScope);
    }
    return null;
  }

  @Override
  public Set<String> getAuthorizedGrantTypes() {

    String grants = client.getAuthorizedGrantTypes();

    if (grants != null) {

      String[] arrayGrant = grants.split(",");

      return Sets.newHashSet(arrayGrant);
    }
    return null;
  }

  @Override
  public Set<String> getRegisteredRedirectUri() {
    String listRegisters = client.getWebServerRedirectUri();
    if (listRegisters != null) {
      String[] registers = listRegisters.split(",");
      return Sets.newHashSet(registers);
    }
    return null;
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public Integer getAccessTokenValiditySeconds() {
    return -1;
  }

  @Override
  public Integer getRefreshTokenValiditySeconds() {
    return -1;
  }

  @Override
  public boolean isAutoApprove(String scope) {
    return true;
  }

  @Override
  public Map<String, Object> getAdditionalInformation() {
    return null;
  }
}
