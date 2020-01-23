package com.voa.goodbam.controller;

import com.voa.goodbam.domain.room.HomeComingStatus;
import com.voa.goodbam.domain.room.InvitationStatus;
import com.voa.goodbam.domain.room.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

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