package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.domain.room.RoomService;
import com.voa.goodbam.domain.room.response.JoinResponse;
import com.voa.goodbam.domain.room.response.RoomResponse;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomRepository roomRepository;
    private final UserStatusInRoomRepository userStatusInRoomRepository;
    private final RoomService roomService;

    public RoomController(RoomRepository roomRepository, UserStatusInRoomRepository userStatusInRoomRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.userStatusInRoomRepository = userStatusInRoomRepository;
        this.roomService = roomService;
    }

    @PostMapping("/new")
    public ResponseEntity room(@RequestParam String roomName, @RequestParam Long userId) {
        try {
            Room newRoom = roomRepository.save(Room.create(roomName));
            userStatusInRoomRepository.save(UserStatusInRoom.create(newRoom, userId));
            return new ResponseEntity(DefaultResponse.of(StatusCode.CREATED, Message.OK, newRoom), HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity(DefaultResponse.of(StatusCode.INTERNAL_SERVER_ERROR, Message.INTERNAL_SEVER_ERROR), HttpStatus.OK);
        }
    }

    @PutMapping("/user")
    public ResponseEntity joinRoom(@RequestParam Long roomId, @RequestParam Long userId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            userStatusInRoomRepository.save(UserStatusInRoom.create(room.get(), userId));
            return new ResponseEntity(DefaultResponse.of(StatusCode.ROOM_JOIN_SUCCESS, Message.ROOM_JOIN_SUCCESS, JoinResponse.builder().isSuccess(true).roomID(room.get().getId()).build()), HttpStatus.OK);
        }
        return new ResponseEntity(DefaultResponse.of(StatusCode.ROOM_DESTROYED, Message.ROOM_DESTROYED, JoinResponse.builder().isSuccess(false).build()), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity leaveRoom(@RequestParam Long roomId, @RequestParam Long userId) {
        try {
            userStatusInRoomRepository.deleteByUserIdAndRoomId(userId, roomId);
        }catch(Exception exception){
            return new ResponseEntity(DefaultResponse.of(StatusCode.INTERNAL_SERVER_ERROR, Message.INTERNAL_SEVER_ERROR, false), HttpStatus.OK);
        }
         return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK, true), HttpStatus.OK);
    }

    @GetMapping(value="/get/{userId}")
    public ResponseEntity getRoomsByUserId(@PathVariable Long userId) {
        List<Room> roomAndUsers = userStatusInRoomRepository.findByUserId(userId).stream().map(UserStatusInRoom::getRoom).collect(Collectors.toList());
        List<RoomResponse> response=new ArrayList<>();
        for(Room room:roomAndUsers){
            response.add(RoomResponse.builder().roomId(room.getId()).roomTitle(room.getName()).build());
        }
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("roomDatas", response);
        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK, responseMap), HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity getRoomInfoByRoomId(@PathVariable Long roomId) {
        return new ResponseEntity(roomService.getRoomInfoByRoomId(roomId), HttpStatus.OK);
    }
}
