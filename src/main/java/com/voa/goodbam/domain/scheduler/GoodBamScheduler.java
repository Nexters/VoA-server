package com.voa.goodbam.domain.scheduler;

import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import com.voa.goodbam.support.ScheduleTaskService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@NoArgsConstructor
public class GoodBamScheduler {

    @Autowired
    private UserStatusInRoomRepository userStatusInRoomRepository;

    public void schedule(long userId, long roomId, ConfirmationType confirmationType) {
        UserStatusInRoom userStatusInRoom = userStatusInRoomRepository.findByUserIdAndRoomId(userId, roomId);
        if (userStatusInRoom.getHomeComingStatus().equals(HomeComingStatus.ON_THE_WAY_HOME)) {
            switch (confirmationType) {
                case FIRST:
                    //send notification, are you home?

                    userStatusInRoom.setConfirmationType(ConfirmationType.SECOND);
                    LocalDateTime tenMinutesFromNow = LocalDateTime.now().plusMinutes(10);
                    ScheduleTaskService.instance.schedule(userStatusInRoom.getTask(), tenMinutesFromNow);
                case SECOND:

                    //send notification, are you home?

                    userStatusInRoom.setConfirmationType(ConfirmationType.THIRD);
                    LocalDateTime twentyMinutesFromNow = LocalDateTime.now().plusMinutes(20);
                    ScheduleTaskService.instance.schedule(userStatusInRoom.getTask(), twentyMinutesFromNow);
                case THIRD:

                    //send notification to friends
                    //send notification to myself, that I sent to all friends

                default:
            }
        }


        /**
         *
         * 언제 누구에게 어떤 메세지를 보낼지 스케쥴링
         *
         */
    }

}

//도착 시간에 귀가처리 안했을때
// 1차 미응답
// 1차 응답 (도착 완료, 또는 시간 연장)
// 2차 시스템 응답 10분 후,
// 3차 미응답, 방 친구한테 보내기

