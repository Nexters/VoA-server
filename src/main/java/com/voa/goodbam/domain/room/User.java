package com.voa.goodbam.domain.room;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String kakaoId;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserStatusInRoom> rooms;
    private boolean isAppUser;
    private String pushCode;
    private int uuid;
    private String os;


    public static User create(String kakaoId, String name, boolean isAppUser, String pushCode, int uuid, String os) {
        return User.builder()
                .kakaoId(kakaoId)
                .name(name)
                .isAppUser(isAppUser)
                .pushCode(pushCode)
                .os(os)
                .uuid(uuid).build();
//        return new User(1, "id", "sang", null, false, "push");
    }

}
