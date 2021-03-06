package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.InvitationStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.domain.scheduler.GoodBamNotifier;
import com.voa.goodbam.repository.UserRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import com.voa.goodbam.support.ScheduleTaskService;
import com.voa.goodbam.support.TimeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;

    @Autowired
    private GoodBamNotifier goodBamNotifier;

    @Value("${status.homecoming.message.to.friends}")
    private String homeComingMessage;

    @Value("${status.arrival.message.to.friends}")
    private String arrivedMessage;

    @Value("${status.arrival.message.to.all}")
    private String allArrivedMessage;

    @PutMapping("/homecoming")
    public ResponseEntity updateHomeComingStatus(@RequestBody Map<String, Object> req) {

        HomeComingStatus homeComingStatus = HomeComingStatus.valueOf(req.get("homeComingStatus").toString());
        long userId = Long.valueOf(req.get("userId").toString());
        long roomId = Long.valueOf(req.get("roomId").toString());
        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        userStatusInRoom.setHomeComingStatus(homeComingStatus);

        String userName = userRepository.findById(userId).get().getName();

        if (homeComingStatus.equals(HomeComingStatus.ON_THE_WAY_HOME)) {
            long limitTime = Long.valueOf(req.get("limitTime").toString());
            userStatusInRoom.setStartedAt(TimeCalculator.getNowLocalDateTime());
            userStatusInRoom.setArrivedAt(TimeCalculator.plusLocalDateTimes(userStatusInRoom.getStartedAt(), limitTime));
            goodBamNotifier.sendNotificationToFriends(roomId, userId, homeComingMessage.replace("{name}", userName));
        } else if (homeComingStatus.equals(HomeComingStatus.ARRIVED_HOME)) {
            userStatusInRoom.setArrivedAt(LocalDateTime.now());
            goodBamNotifier.sendNotificationToFriends(roomId, userId, arrivedMessage.replace("{name}", userName));
            /**
             * 모두 도착했을 시 메시지 만들기
             */
        }
        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK, userStatusInRoomRepository.save(userStatusInRoom)), HttpStatus.OK);
    }

    @PutMapping("/invitation")
    public ResponseEntity updateInvitationStatus(@RequestBody Map<String, Object> req) {
        InvitationStatus invitationStatus = InvitationStatus.valueOf(req.get("invitationStatus").toString());
        long userId = Long.valueOf(req.get("userId").toString());
        long roomId = Long.valueOf(req.get("roomId").toString());

        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        userStatusInRoom.setInvitationStatus(invitationStatus);
        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK, userStatusInRoomRepository.save(userStatusInRoom)), HttpStatus.OK);
    }

    @PutMapping("/time/remain")
    public ResponseEntity updateRemainingTime(@RequestBody Map<String, Object> req) {

        long timeRemaining = Integer.valueOf(req.get("timeRemaining").toString());
        long userId = Long.valueOf(req.get("userId").toString());
        long roomId = Long.valueOf(req.get("roomId").toString());

        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        LocalDateTime expectedArrivalTime = LocalDateTime.now().plusMinutes(timeRemaining);
        userStatusInRoom.setExpectedToArriveAt(expectedArrivalTime);
        new ScheduleTaskService().schedule(userStatusInRoom.getTask(), expectedArrivalTime);
        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK, userStatusInRoomRepository.save(userStatusInRoom)), HttpStatus.OK);
    }
}
