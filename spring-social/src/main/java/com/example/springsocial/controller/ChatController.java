package com.example.springsocial.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  @MessageMapping("/send.message")
  public void sendMessage() {

  }

}
