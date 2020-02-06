package com.voa.goodbam.room.api;

import com.voa.goodbam.common.response.DefaultResponse;
import com.voa.goodbam.common.response.Message;
import com.voa.goodbam.common.response.StatusCode;
import com.voa.goodbam.room.domain.Room;
import com.voa.goodbam.room.domain.RoomRepository;
import com.voa.goodbam.room.dto.RoomResponse;
import com.voa.goodbam.status.domain.UserStatusInRoom;
import com.voa.goodbam.status.domain.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;
    @PostMapping("/new")
    public ResponseEntity room(@RequestParam String roomName, @RequestParam Long userId) {
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
    public ResponseEntity getRoomsByUserId(@PathVariable Long userId) {

        List<Room> rooms = roomRepository.findByUsers_User_Id(userId);
        if(rooms.size()==0){
            return new ResponseEntity(DefaultResponse.of(StatusCode.NO_CONTENT, Message.NO_CONTENT,rooms) , HttpStatus.OK);
        }
        List<RoomResponse> roomDatas = new ArrayList<>();
        for(Room room:rooms){
            roomDatas.add(RoomResponse.builder().roomId(room.getId()).roomTitle(room.getName()).build());
        }

        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK,roomDatas) , HttpStatus.OK);
    }

}
