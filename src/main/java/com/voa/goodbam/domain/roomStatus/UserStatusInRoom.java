package com.voa.goodbam.domain.roomStatus;

import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserStatus")
@Getter
@Setter
@Builder
public class UserStatusInRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private HomeComingStatus userState;
    @Enumerated(EnumType.STRING)
    private InvitationStatus invitationStatus;
    private LocalDateTime startTime;
    private LocalDateTime arrivalTime;
    public static UserStatusInRoom create(Room room, long userId) {
        return UserStatusInRoom.builder()
                .room(room)
                .user(User.builder().id(userId).build())
                .userState(HomeComingStatus.NOT_INITIATED)
                .invitationStatus(InvitationStatus.ACCEPTED)
                .build();
//        return new User(1, "id", "sang", null, false, "push");
    }
}
