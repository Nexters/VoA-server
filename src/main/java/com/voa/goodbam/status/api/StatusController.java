package com.voa.goodbam.status.api;

import com.voa.goodbam.status.type.HomeComingStatus;
import com.voa.goodbam.status.type.InvitationStatus;
import com.voa.goodbam.user.domain.User;
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
