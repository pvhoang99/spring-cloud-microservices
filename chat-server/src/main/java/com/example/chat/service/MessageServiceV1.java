package com.example.chat.service;

import com.example.chat.dao.entity.MessageEntity;
import com.example.chat.dao.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceV1 {

  public final MessageRepository messageRepository;

  public MessageEntity findById(Long id) {
    return messageRepository.findById(id).orElse(null);
  }

  public MessageEntity save(MessageEntity messageEntity) {
    log.debug("====MessageServiceV1 save {}", messageEntity);
    return messageRepository.save(messageEntity);
  }

}
