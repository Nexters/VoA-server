package com.voa.goodbam.controller;

import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/new")
    public Room room(@RequestParam String roomName, @RequestParam long userId) {
        Room newRoom = roomRepository.save(Room.create(roomName));
        userStatusInRoomRepository.save(UserStatusInRoom.create(newRoom, userId));
        return newRoom;
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

}
