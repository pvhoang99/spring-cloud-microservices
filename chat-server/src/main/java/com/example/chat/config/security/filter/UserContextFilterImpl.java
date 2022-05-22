package com.example.chat.config.security.filter;

import com.example.chat.client.AuthServiceFeignClient;
import com.example.chat.dto.UserDTO;
import com.example.common.filter.UserContextFilter;
import com.example.common.utils.UserContextHolder.UserContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilterImpl extends UserContextFilter {

  @Setter(onMethod = @__({@Autowired}))
  private AuthServiceFeignClient authServiceFeignClient;

  @Override
  public UserContext getUserContext() {
    UserDTO user = authServiceFeignClient.getCurrentUser();
    return new UserContext(user.getUserId(), user.getUsername(),
        user.getFullName(), user.getEmail());
  }
}
