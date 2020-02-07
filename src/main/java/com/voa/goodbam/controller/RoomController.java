package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.domain.room.RoomService;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;
    @Autowired
    private RoomService roomService;
    @PostMapping("/new")
    public ResponseEntity room(@RequestParam String roomName, @RequestParam Long userId) {
        Room newRoom = roomRepository.save(Room.create(roomName));
        userStatusInRoomRepository.save(UserStatusInRoom.create(newRoom, userId));
        return new ResponseEntity(DefaultResponse.of(StatusCode.CREATED, Message.OK, newRoom), HttpStatus.OK);
    }

    @PutMapping("/user")
    public Room joinRoom(@RequestParam long roomId, @RequestParam long userId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            userStatusInRoomRepository.save(UserStatusInRoom.create(room.get(), userId));
            return room.get();
        }
        return null;
    }

    @DeleteMapping("/user")
    public boolean leaveRoom(@RequestParam long roomId, @RequestParam long userId) {
        userStatusInRoomRepository.deleteByUserIdAndRoomId(userId, roomId);
        return true;
    }

    @GetMapping(value="/get/{userId}")
    public List<Room> getRoomsByUserId(@PathVariable Long userId) {
        List<Room> roomAndUsers = userStatusInRoomRepository.findByUserId(userId).stream().map(UserStatusInRoom::getRoom).collect(Collectors.toList());
        return roomAndUsers;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity getRoomInfoByRoomId(@PathVariable Long roomId) {
        return new ResponseEntity(roomService.getRoomInfoByRoomId(roomId), HttpStatus.OK);
    }
}
