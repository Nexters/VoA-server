package com.voa.goodbam.domain.scheduler;

import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.repository.UserRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import com.voa.goodbam.support.ScheduleTaskService;
import com.voa.goodbam.support.pushnotification.IOSPush;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class GoodBamScheduler {

    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${confirm.home.first.message.to.me}")
    private String messageFirstToMe;

    @Value("${confirm.home.second.message.to.me}")
    private String messageSecondToMe;

    @Value("${confirm.home.third.message.to.me}")
    private String messageThirdToMe;

    @Value("${confirm.home.third.message.to.friends}")
    private String messageThirdToFriends;

    public void schedule(long userId, long roomId, ConfirmationType confirmationType) {
        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        if (userStatusInRoom.getHomeComingStatus().equals(HomeComingStatus.ON_THE_WAY_HOME)) {
            switch (confirmationType) {
                case FIRST:
                    userRepository.findById(userId).get().sendNotification(messageFirstToMe);
                    userStatusInRoom.setConfirmationType(ConfirmationType.SECOND);
                    LocalDateTime tenMinutesFromNow = LocalDateTime.now().plusMinutes(10);
                    ScheduleTaskService.instance.schedule(userStatusInRoom.getTask(), tenMinutesFromNow);
                case SECOND:
                    userRepository.findById(userId).get().sendNotification(messageSecondToMe);
                    userStatusInRoom.setConfirmationType(ConfirmationType.THIRD);
                    LocalDateTime twentyMinutesFromNow = LocalDateTime.now().plusMinutes(20);
                    ScheduleTaskService.instance.schedule(userStatusInRoom.getTask(), twentyMinutesFromNow);
                case THIRD:
                    userRepository.findById(userId).get().sendNotification(messageThirdToMe);
                    List<String> registrationTokens =
                            userStatusInRoomRepository.findByRoomId(roomId).stream().
                            map(UserStatusInRoom::getUser).
                            map(User::getFcmRegisterationToken).
                            filter(token -> !token.isEmpty()).collect(Collectors.toList());
                    IOSPush.sendNotifications(registrationTokens, messageThirdToFriends);
                default:
            }
        }
    }

}

//도착 시간에 귀가처리 안했을때
// 1차 미응답
// 1차 응답 (도착 완료, 또는 시간 연장)
// 2차 시스템 응답 10분 후,
// 3차 미응답, 방 친구한테 보내기

