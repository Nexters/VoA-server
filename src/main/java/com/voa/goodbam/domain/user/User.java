package com.voa.goodbam.domain.user;

import com.voa.goodbam.domain.messenger.Messenger;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kakaoId;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserStatusInRoom> rooms;
    private boolean isAppUser;
    private String kakaoAccessToken;
    private String uuid;
    private String os;
    private String profileImage;
    @OneToMany(mappedBy = "sender")
    private List<Messenger> sendMessages;
    @OneToMany(mappedBy = "target")
    private List<Messenger> receivedMessages;
}
