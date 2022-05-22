package com.example.chat.config.security.aop;

import com.example.chat.dao.entity.UserEntity;
import com.example.chat.service.RoomServiceV1;
import com.example.common.utils.UserContextHolder;
import java.util.Set;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

  @Setter(onMethod = @__({@Autowired}))
  private RoomServiceV1 roomServiceV1;

  @Override
  public boolean isMember(Long roomId) {

    Set<UserEntity> members = roomServiceV1.findById(roomId).getMembers();
    if (members.isEmpty()) {
      return false;
    }
    return members.stream()
        .anyMatch(e -> e.getUserId().equals(UserContextHolder.getContext().getUserId()));
  }
}
