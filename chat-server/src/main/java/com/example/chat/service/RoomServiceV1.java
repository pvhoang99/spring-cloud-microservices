package com.example.chat.service;

import com.example.chat.dao.entity.RoomEntity;
import com.example.chat.dao.repositoty.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceV1 {

  private final RoomRepository roomRepository;

  public RoomEntity findById(Long id) {
    return roomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("room not exist with id: " + id));
  }

}
