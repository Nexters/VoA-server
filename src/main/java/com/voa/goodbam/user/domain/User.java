package com.voa.goodbam.user.domain;

import com.voa.goodbam.status.domain.UserStatusInRoom;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kakaoId;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserStatusInRoom> rooms;
    private boolean isAppUser;
    private String pushCode;
    private int uuid;
    private String os;


    public static User create(User user) {
        return User.builder()
                .kakaoId(user.getKakaoId())
                .name(user.getName())
                .isAppUser(user.isAppUser())
                .pushCode(user.getPushCode())
                .os(user.getOs())
                .uuid(user.getUuid()).build();
//        return new User(1, "id", "sang", null, false, "push");
    }

}
