package com.example.chat.service;

import com.example.chat.dao.entity.MessageEntity;
import com.example.chat.dao.repositoty.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceV1 {

  public final MessageRepository messageRepository;

  public MessageEntity findById(Long id) {
    return messageRepository.findById(id).orElse(null);
  }

}