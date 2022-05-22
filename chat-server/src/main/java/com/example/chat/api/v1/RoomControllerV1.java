package com.example.chat.api.v1;

import com.example.chat.dao.entity.RoomEntity;
import com.example.chat.dto.EditMemberDTO;
import com.example.chat.service.RoomServiceV1;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomControllerV1 {

  private final RoomServiceV1 roomService;

  @PostMapping("/create")
  public ResponseEntity<?> createRoom(@RequestBody RoomEntity roomEntity) {
    return ResponseEntity.ok(roomService.createRoom(roomEntity));
  }

  @PostMapping("/add-members")
  public ResponseEntity<?> addMembers(@RequestBody @Valid EditMemberDTO editMemberDTO) {
    return ResponseEntity.ok(roomService.addMember(editMemberDTO));
  }

  @PostMapping("/remove-members")
  public ResponseEntity<?> removeMembers(@RequestBody @Valid EditMemberDTO editMemberDTO) {
    roomService.deleteMember(editMemberDTO);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllRoomBelong() {
    return ResponseEntity.ok(roomService.getAllRoomBelong());
  }
}
