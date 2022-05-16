package com.example.shoppingcartservice.security;

import com.example.common.filter.UserContextFilter;
import com.example.common.utils.UserContextHolder.UserContext;
import com.example.shoppingcartservice.client.AuthServiceFeignClient;
import com.example.shoppingcartservice.dto.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilterImpl extends UserContextFilter {

  @Setter(onMethod = @__({@Autowired}))
  private AuthServiceFeignClient authServiceFeignClient;

  @Override
  public UserContext getUserContext() {
    User user = authServiceFeignClient.getCurrentUser();
    return new UserContext(user.getUserId(), user.getUsername(),
        user.getFullName(), user.getEmail());
  }
}
