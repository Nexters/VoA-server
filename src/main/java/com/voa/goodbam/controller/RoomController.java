package com.voa.goodbam.controller;

import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;
    @PostMapping("/new")
    public ResponseEntity room(@RequestParam String roomName, @RequestParam long userId) {
        Room newRoom = roomRepository.save(Room.create(roomName));
        try{
            userStatusInRoomRepository.save(UserStatusInRoom.create(newRoom, userId));
        }catch (Exception exception){
            roomRepository.delete(newRoom);
        }
        return null;
    }

    @PutMapping("/user")
    public ResponseEntity joinRoom(@RequestParam long roomId, @RequestParam String kakaoId) {

        return null;
    }

    @DeleteMapping("/user")
    public ResponseEntity leaveRoom(@RequestParam long roomId, @RequestParam String userId) {

        return null;
    }

    @GetMapping("/get/{userId}")
    public List<Room> getRoomsByUserId(@PathVariable Long userId) {
        return null;
    }

}
