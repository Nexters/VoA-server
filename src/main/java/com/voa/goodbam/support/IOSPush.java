package com.voa.goodbam.support;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

public class IOSPush {

    @PostConstruct
    private void initializeIOS() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("path/to/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://voaservice-cf8bf.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);

        } catch (Exception e) {

        }

    }

    public void sendNotification() {
        //https://firebase.google.com/docs/cloud-messaging/send-message
        String registrationToken = "AAAA697aE94:APA91bHHaB9fGfpKjZTjbxp8EsSgmmX_oS_pEgHePOdMzMC8UGriQvVA6tinmq8yWicCXPgaEYSHW2ELOwFrtR-ggB0OSgwyN0Heve-_DZ83_fMKCskEoU3PoNEJw676HfkPpXHpgZbi";

            Message message = Message.builder()
                    .putData("score", "850")
                    .setToken(registrationToken)
                    .build();

            try {
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message: " + response);

            } catch (Exception e) {

            }
    }

}
