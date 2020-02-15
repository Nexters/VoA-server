package com.voa.goodbam.support.pushnotification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Slf4j
@Component
public class IOSPush {

    @PostConstruct
    private void initializeIOS() {
        log.info("Init firebase");
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("/voa/voaservice-cf8bf-firebase-adminsdk-xffaz-e2511636b1.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://voaservice-cf8bf.firebaseio.com")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }


    @Scheduled(fixedDelay = 10000)
    public void sendNotification() {
        String registrationToken = "dPcYNcKyW09mmTfmeuQD9p:APA91bGp5icdr4i17RT8qX8Fp-9tQnMSTqAKMG7_MBodvMaTOnkRnAW7dVOWXQDpqdfncrvuGanwXvFR_jvA1AYGxymu7Lbne3fKFg2w3GoOmoL1DzMFQQEhO5lpk0XbXJtozIsAFRu7";

        Message message = Message.builder()
                .putData("HELLLOOO", "HIHIHIHIHHI")
                .setToken(registrationToken)
                .setNotification(Notification.builder().setBody("body").setTitle("title").build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully sent message: " + response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
