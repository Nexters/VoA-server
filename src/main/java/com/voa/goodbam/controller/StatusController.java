package com.voa.goodbam.controller;

import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.InvitationStatus;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
