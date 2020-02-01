package com.voa.goodbam.domain.notification;

import com.voa.goodbam.domain.room.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class NotificationScheduler {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private void schedule(NotificationType notificationType, User user, LocalDateTime time) {
        taskScheduler.schedule(new RunnableTask("a"), Instant.from(time));
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

    class RunnableTask implements Runnable{

        private String message;

        public RunnableTask(String message){
            this.message = message;
        }
        @Override
        public void run() {
            System.out.println("Runnable Task with "+message+" on thread "+Thread.currentThread().getName());
        }
    }
}
