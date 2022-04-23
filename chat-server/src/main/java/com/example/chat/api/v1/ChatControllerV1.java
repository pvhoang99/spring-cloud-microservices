package com.example.chat.api.v1;

import com.example.chat.dao.entity.MessageEntity;
import com.example.chat.dao.entity.RoomEntity;
import com.example.chat.service.ChatServiceV1;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatControllerV1 {

  private final ChatServiceV1 chatServiceV1;

  @PostMapping("/api/v1/chat")
  @ResponseBody
  public ResponseEntity<?> chat(@RequestBody MessageEntity messageEntity) {
    return ResponseEntity.ok(chatServiceV1.chat(messageEntity));
  }

  @PostMapping("/api/v1/chat/create-room")
  @ResponseBody
  public ResponseEntity<?> createRoom(@RequestBody RoomEntity roomEntity) {
    return ResponseEntity.ok(chatServiceV1.createGroup(roomEntity));
  }

  @PostMapping("/api/v1/chat/join-room")
  @ResponseBody
  public ResponseEntity<?> joinRoom(@RequestBody RoomEntity roomEntity) {
    return ResponseEntity.ok(chatServiceV1.joinRoom(roomEntity));
  }

  @MessageMapping("/send.message")
  public void sendMessage(@Payload MessageEntity messageEntity, Principal principal) {
    chatServiceV1.sendMessage(messageEntity, principal);
  }

  @SubscribeMapping("/old.message/{userId}")
  public ResponseEntity<?> oldMessage(@DestinationVariable("userId") Long id) {
    log.info("====ChatControllerV1 oldMessage subscribeMapping old message with userId ---> {}", id);
    return ResponseEntity.ok(chatServiceV1.getOldMessage(id));
  }
}
