package com.voa.goodbam.controller;

import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/new")
    public Room room(@RequestBody String roomName) {

        roomRepository.save(Room.create(roomName));

        return null;
    }

    @PutMapping("/user")
    public Room joinRoom(@RequestParam long roomId, @RequestParam String kakaoId) {

        return null;
    }

    @DeleteMapping("/user")
    public Room leaveRoom(@RequestParam long roomId, @RequestParam String userId) {

        return null;
    }

    @GetMapping("/get/{userId}")
    public List<Room> getRoomsByUserId(@PathVariable Long userId) {
        return null;
    }

}