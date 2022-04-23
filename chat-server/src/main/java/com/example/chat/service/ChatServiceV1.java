package com.example.chat.service;

import com.example.chat.dao.entity.MessageEntity;
import com.example.chat.dao.entity.RoomEntity;
import com.example.chat.dao.entity.UserEntity;
import com.example.chat.dao.repository.MessageRepository;
import com.example.chat.dao.repository.RoomRepository;
import com.example.chat.dao.repository.UserRepository;
import com.example.chat.type.ChatType;
import com.google.gson.Gson;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceV1 {

  private final Gson gson;

  private final SimpMessagingTemplate webSocketMessagingTemplate;

  private final MessageRepository messageRepository;

  private final UserRepository userRepository;

  private final RoomRepository roomRepository;

  private final UserServiceV1 userServiceV1;

  private final MessageServiceV1 messageServiceV1;

  private final RoomServiceV1 roomServiceV1;

  private final Executor executor;

  public MessageEntity chat(MessageEntity messageEntity) {

    UserEntity from = userRepository.findById(messageEntity.getFromUser()).orElse(null);
    UserEntity to = userRepository.findById(messageEntity.getToUser()).orElse(null);
    messageEntity.setUserSend(from);
    messageEntity.setUserReceive(to);
    return messageRepository.save(messageEntity);

  }

  public RoomEntity createGroup(RoomEntity roomEntity) {
    return roomRepository.save(roomEntity);
  }

  public RoomEntity joinRoom(RoomEntity roomEntity) {

    RoomEntity entity = roomRepository.findById(roomEntity.getId()).orElse(null);

    UserEntity userEntity = userRepository.findById(roomEntity.getUserId()).orElse(null);
    assert entity != null;
    entity.setMembers(Collections.singletonList(userEntity));
    return roomRepository.save(entity);

  }

  public void sendMessage(MessageEntity messageEntity, Principal principal) {

    if (ChatType.PERSONAL.equals(messageEntity.getChatType())) {
      this.sendPrivateMessage(messageEntity, principal);
    } else if (ChatType.GROUP.equals(messageEntity.getChatType())) {
      this.sendPublicMessage(messageEntity);
    } else {
      throw new RuntimeException();
    }
  }

  private void sendPrivateMessage(MessageEntity messageEntity, Principal principal) {
    UserEntity to = userServiceV1.findByUserId(messageEntity.getToUser());
    UserEntity from = userServiceV1.findByUsername(principal.getName());
    messageEntity.setUserSend(from);
    messageEntity.setUserReceive(to);
    messageServiceV1.save(messageEntity);

    CompletableFuture
        .runAsync(() -> webSocketMessagingTemplate.convertAndSendToUser(from.getUsername(),
            "/queue/reply", gson.toJson(messageEntity)), executor)
        .thenRun(
            () -> webSocketMessagingTemplate.convertAndSendToUser(to.getUsername(), "/queue/reply",
                gson.toJson(messageEntity)));

  }

  private void sendPublicMessage(MessageEntity messageEntity) {

    RoomEntity roomEntity = roomServiceV1.findById(messageEntity.getToGroup());

    List<UserEntity> listMember = roomEntity.getMembers();

    listMember.parallelStream().forEach(userEntity -> CompletableFuture.runAsync(
        () -> webSocketMessagingTemplate.convertAndSendToUser(userEntity.getUsername(),
            "/user/queue",
            messageEntity.getText()), executor));

    messageEntity.setRoomEntity(roomEntity);

    messageServiceV1.save(messageEntity);
  }

  public List<MessageEntity> getOldMessage(Long userId) {
    UserEntity userEntity = this.userServiceV1.getCurrentUser();
    log.debug("currentUserId {}, friendId {}", userEntity.getUserId(), userId);

    Streamable<MessageEntity> oldMessages = messageRepository.oldMessage(userEntity.getUserId(),
        userId);
    return oldMessages.toList();
  }

}
