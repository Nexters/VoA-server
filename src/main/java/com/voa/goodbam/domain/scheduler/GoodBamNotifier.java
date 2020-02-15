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
public class GoodBamNotifier {

    @Autowired
    private static UserStatusInRoomRepository userStatusInRoomRepository;

    @Autowired
    private static UserRepository userRepository;

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
                    sendNotificationToMe(userId, messageFirstToMe);
                    userStatusInRoom.setConfirmationType(ConfirmationType.SECOND);
                    LocalDateTime tenMinutesFromNow = LocalDateTime.now().plusMinutes(10);
                    ScheduleTaskService.instance.schedule(userStatusInRoom.getTask(), tenMinutesFromNow);
                case SECOND:
                    sendNotificationToMe(userId, messageSecondToMe);
                    userStatusInRoom.setConfirmationType(ConfirmationType.THIRD);
                    LocalDateTime twentyMinutesFromNow = LocalDateTime.now().plusMinutes(20);
                    ScheduleTaskService.instance.schedule(userStatusInRoom.getTask(), twentyMinutesFromNow);
                case THIRD:
                    sendNotificationToMe(userId, messageThirdToMe);
                    sendNotificationToFriends(roomId, userId, messageThirdToFriends);
                default:
            }
        }
        userStatusInRoomRepository.save(userStatusInRoom);
    }

    private static List<String> getFriendsRegistrationTokens(long roomId, long userId) {
        List<String> registrationTokens =
                userStatusInRoomRepository.findByRoomId(roomId).stream().
                        map(UserStatusInRoom::getUser).
                        filter(user -> user.getId() != userId).
                        map(User::getFcmRegisterationToken).
                        filter(token -> token==null).collect(Collectors.toList());
        return registrationTokens;
    }

    public static void sendNotificationToMe(long userId, String message) {
        String fcmToken = userRepository.findById(userId).get().getFcmRegisterationToken();
        if (fcmToken!=null) {
            IOSPush.sendNotification(fcmToken, message);
        }
    }

    public static void sendNotificationToFriends(long roomId, long userId, String message) {
        List<String> registrationTokens = GoodBamNotifier.getFriendsRegistrationTokens(roomId, userId);
        IOSPush.sendNotifications(registrationTokens, message);
        //kakao push
    }

}

//도착 시간에 귀가처리 안했을때
// 1차 미응답
// 1차 응답 (도착 완료, 또는 시간 연장)
// 2차 시스템 응답 10분 후,
// 3차 미응답, 방 친구한테 보내기

