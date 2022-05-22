package com.example.chat.service;

import com.example.chat.dao.entity.RoomEntity;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.dao.repository.RoomRepository;
import com.example.chat.dto.EditMemberDTO;
import com.example.common.utils.UserContextHolder;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceV1 {

  private final RoomRepository roomRepository;
  private final UserServiceV1 userService;

  public RoomEntity findById(Long id) {
    return roomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("room not exist with id: " + id));
  }

  public RoomEntity createRoom(RoomEntity roomEntity) {
    return roomRepository.save(roomEntity);
  }

  public RoomEntity addMember(EditMemberDTO editMemberDTO) {
    this.validateEditMember(editMemberDTO);
    Set<UserEntity> users = userService.findByIds(editMemberDTO.getUserIds());

    RoomEntity roomEntity = this.findById(editMemberDTO.getRoomId());
    roomEntity.setMembers(users);
    return roomEntity;
  }

  private void validateEditMember(EditMemberDTO editMemberDTO) {
    if (Objects.isNull(editMemberDTO)) {
      log.error("====RoomServiceV1 validateEditMember EditMemberDTO is null====");
      throw new RuntimeException("EditMemberDTO is null");
    }
    if (editMemberDTO.getUserIds().isEmpty()) {
      log.error("====RoomServiceV1 validateEditMember list userIds is null====");
      throw new RuntimeException("list userIds is null");
    }
    if (Objects.isNull(editMemberDTO.getRoomId())) {
      log.error("====RoomServiceV1 validateEditMember roomId is null====");
      throw new RuntimeException("roomId is null");
    }
  }

  public void deleteMember(EditMemberDTO editMemberDTO) {
    this.validateEditMember(editMemberDTO);
    roomRepository.removeUser(editMemberDTO.getUserIds(), editMemberDTO.getRoomId());
  }

  public Set<RoomEntity> getAllRoomBelong() {
    return roomRepository.getRoomBelong(UserContextHolder.getContext().getUserId());

  }

}
