package com.voa.goodbam.model.domain.notification;

import com.voa.goodbam.model.domain.room.User;

import java.time.LocalDateTime;

public class NotificationScheduler {

    private void schedule(NotificationType notificationType, User user, LocalDateTime time) {
        /**
         *
         * 언제 누구에게 어떤 메세지를 보낼지 스케쥴링
         *
         */
    }

    private void schedule(NotificationType notificationType, User user, LocalDateTime time, User fromUser) {
        /**
         *
         * 언제 누구에게 누가 어떤 메세지를 보낼지 스케쥴링
         *
         */
    }
}
