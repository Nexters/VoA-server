package com.voa.goodbam.model.domain.room;

import com.voa.goodbam.model.dto.user.CreateUserDto;
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


    public static User create(CreateUserDto createUserDto) {
        return User.builder()
                .kakaoId(createUserDto.getKakaoId())
                .name(createUserDto.getName())
                .isAppUser(createUserDto.isAppUser())
                .pushCode(createUserDto.getPushCode())
                .os(createUserDto.getOs())
                .uuid(createUserDto.getUuid()).build();
//        return new User(1, "id", "sang", null, false, "push");
    }

}
