package com.voa.goodbam.domain.scheduler;

import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.user.User;

import java.time.LocalDateTime;

public class GoodBamScheduler {

    public static void schedule(HomeComingStatus homeComingStatus, long userId, LocalDateTime time) {
//        NotificationType notificationType

        
        /**
         *
         * 언제 누구에게 어떤 메세지를 보낼지 스케쥴링
         *
         */
    }

    public static void schedule(NotificationType notificationType, User user, LocalDateTime time, User fromUser) {
        /**
         *
         * 언제 누구에게 누가 어떤 메세지를 보낼지 스케쥴링
         *
         */
    }

}
