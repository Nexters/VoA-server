package com.voa.goodbam.support.pushnotification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.List;

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

    public static void sendNotifications(List<String> registrationTokens, String body) {
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(registrationTokens)
                .setNotification(Notification.builder().setBody(body).build())
                .build();
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            log.info("Successfully sent message: " + response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void sendNotification(String registrationToken, String body) {
//        String registrationToken = "dPcYNcKyW09mmTfmeuQD9p:APA91bGp5icdr4i17RT8qX8Fp-9tQnMSTqAKMG7_MBodvMaTOnkRnAW7dVOWXQDpqdfncrvuGanwXvFR_jvA1AYGxymu7Lbne3fKFg2w3GoOmoL1DzMFQQEhO5lpk0XbXJtozIsAFRu7";
        Message message = Message.builder()
                .setToken(registrationToken)
                .setNotification(Notification.builder().setBody(body).build())
                .build();
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully sent message: " + response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
