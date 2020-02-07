package com.voa.goodbam.controller;

import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.InvitationStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final UserStatusInRoomRepository userStatusInRoomRepository;

    public StatusController(UserStatusInRoomRepository userStatusInRoomRepository) {
        this.userStatusInRoomRepository = userStatusInRoomRepository;
    }

    @PutMapping("/homecoming")
    public UserStatusInRoom updateHomeComingStatus(@RequestParam HomeComingStatus homeComingStatus,
                                       @RequestParam long userId,
                                       @RequestParam long roomId) {

        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        userStatusInRoom.setHomeComingStatus(homeComingStatus);
        return userStatusInRoomRepository.save(userStatusInRoom);
    }

    @PutMapping("/invitation")
    public UserStatusInRoom updateInvitationStatus(@RequestParam InvitationStatus invitationStatus,
                                       @RequestParam long userId,
                                       @RequestParam long roomId) {
        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        userStatusInRoom.setInvitationStatus(invitationStatus);
        return userStatusInRoomRepository.save(userStatusInRoom);
    }
}
