package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.domain.room.RoomResponse;
import com.voa.goodbam.domain.room.RoomService;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
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
    @Autowired
    private RoomService roomService;
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
        if(rooms.isEmpty()){
            return new ResponseEntity(DefaultResponse.of(StatusCode.NO_CONTENT, Message.NO_CONTENT,rooms) , HttpStatus.OK);
        }
        List<RoomResponse> roomDatas = new ArrayList<>();
        for(Room room:rooms){
            roomDatas.add(RoomResponse.builder().roomId(room.getId()).roomTitle(room.getName()).build());
        }
        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK,roomDatas) , HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity getRoomInfoByRoomId(@PathVariable Long roomId) {
        return new ResponseEntity(roomService.getRoomInfoByRoomId(roomId), HttpStatus.OK);
    }
}
