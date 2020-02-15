package com.voa.goodbam.controller;

import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.InvitationStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.domain.scheduler.GoodBamNotifier;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import com.voa.goodbam.support.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;

    @Value("${status.homecoming.message.to.friends}")
    private String homeComingMessage;

    @Value("${status.arrival.message.to.friends}")
    private String arrivedMessage;

    @Value("${status.arrival.message.to.all}")
    private String allArrivedMessage;

    @PutMapping("/homecoming")
    public UserStatusInRoom updateHomeComingStatus(@RequestBody Map<String, Object> req) {

        HomeComingStatus homeComingStatus = HomeComingStatus.valueOf(req.get("homeComingStatus").toString());
        long userId = Long.valueOf(req.get("userId").toString());
        long roomId = Long.valueOf(req.get("roomId").toString());

        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        userStatusInRoom.setHomeComingStatus(homeComingStatus);
        if (homeComingStatus.equals(HomeComingStatus.ON_THE_WAY_HOME)) {
            userStatusInRoom.setStartedAt(LocalDateTime.now());
            GoodBamNotifier.sendNotificationToFriends(roomId, userId, homeComingMessage);
        } else if (homeComingStatus.equals(HomeComingStatus.ARRIVED_HOME)) {
            userStatusInRoom.setArrivedAt(LocalDateTime.now());
            GoodBamNotifier.sendNotificationToFriends(roomId, userId, arrivedMessage);
            /**
             * 모두 도착했을 시 메시지 만들기
             */
        }
        return userStatusInRoomRepository.save(userStatusInRoom);
    }

    @PutMapping("/invitation")
    public UserStatusInRoom updateInvitationStatus(@RequestBody Map<String, Object> req) {
        InvitationStatus invitationStatus = InvitationStatus.valueOf(req.get("invitationStatus").toString());
        long userId = Long.valueOf(req.get("userId").toString());
        long roomId = Long.valueOf(req.get("roomId").toString());

        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        userStatusInRoom.setInvitationStatus(invitationStatus);
        return userStatusInRoomRepository.save(userStatusInRoom);
    }

    @PutMapping("/time/remain")
    public UserStatusInRoom updateRemainingTime(@RequestBody Map<String, Object> req) {

        long timeRemaining = Integer.valueOf(req.get("timeRemaining").toString());
        long userId = Long.valueOf(req.get("userId").toString());
        long roomId = Long.valueOf(req.get("roomId").toString());

        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        LocalDateTime expectedArrivalTime = LocalDateTime.now().plusMinutes(timeRemaining);
        userStatusInRoom.setExpectedToArriveAt(expectedArrivalTime);
        new ScheduleTaskService().schedule(userStatusInRoom.getTask(), expectedArrivalTime);
        return userStatusInRoomRepository.save(userStatusInRoom);
    }
}
