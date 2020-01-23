package com.voa.goodbam.domain.room;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kakaoId;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserStatusInRoom> rooms;
    private boolean isAppUser;
    private String pushCode;
    private int uuid;
    private String os;


//    public static User create() {
//        return new User(1, "id", "sang", null, false, "push");
//    }

}
