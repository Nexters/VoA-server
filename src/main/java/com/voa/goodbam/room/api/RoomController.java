package com.voa.goodbam.room.api;

import com.voa.goodbam.room.domain.Room;
import com.voa.goodbam.status.domain.UserStatusInRoom;
import com.voa.goodbam.room.domain.RoomRepository;
import com.voa.goodbam.status.domain.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Room room(@RequestParam String roomName, @RequestParam long userId) {
        Room newRoom = roomRepository.save(Room.create(roomName));
        try{
            userStatusInRoomRepository.save(UserStatusInRoom.create(newRoom, userId));
        }catch (Exception exception){
            roomRepository.delete(newRoom);
        }
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
