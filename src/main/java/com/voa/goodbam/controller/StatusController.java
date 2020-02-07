package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.InvitationStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import com.voa.goodbam.domain.room.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    UserStatusInRoomRepository userStatusInRoomRepository;
    @PutMapping("/homecoming")
    public User updateHomeComingStatus(@RequestParam HomeComingStatus homeComingStatus,
                                       @RequestParam String userId,
                                       @RequestParam String roomId) {

        return null;
    }

    @PutMapping("/invitation")
    public User updateInvitationStatus(@RequestParam InvitationStatus invitationStatus,
                                       @RequestParam String userId,
                                       @RequestParam String roomId) {

        return null;
    }
    @GetMapping("/get/{roomId}")
    public ResponseEntity getRoomInfoByRoomId(@PathVariable Long roomId) {
        List<UserStatusInRoom> userInfos = userStatusInRoomRepository.findByRoomId(roomId);
        if(userInfos.size()==0){
            return new ResponseEntity(DefaultResponse.of(StatusCode.NO_CONTENT, Message.NO_CONTENT) , HttpStatus.OK);
        }
        List<UserInfoResponse> participants = new ArrayList<>();
        for(UserStatusInRoom userInfo:userInfos){
            User user = userInfo.getUser();
//            participants.add(UserInfoResponse.builder().isMessage(user.isAppUser()).userID(user.getId()).userName(user.getName()).userStatus(userInfo.getUserState().name()).responseTime(userInfo.setArrivalTime()).build());
        }
        String userProfileURL;
        String elapsedTime;
        String totalTime;
        String responseTime;
        Boolean isMessage;
        return null;
    }

}
