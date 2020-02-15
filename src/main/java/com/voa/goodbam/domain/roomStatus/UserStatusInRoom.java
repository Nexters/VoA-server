package com.voa.goodbam.domain.roomStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.domain.scheduler.ConfirmationType;
import com.voa.goodbam.domain.scheduler.GoodBamScheduler;
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
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","room", "user"})
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
    private HomeComingStatus homeComingStatus;

    @Enumerated(EnumType.STRING)
    private InvitationStatus invitationStatus;

    private LocalDateTime startedAt;
    private LocalDateTime arrivedAt;
    private LocalDateTime expectedToArriveAt;
    private ConfirmationType confirmationType;

    public static UserStatusInRoom create(Room room, long userId) {
        return UserStatusInRoom.builder()
                .room(room)
                .user(User.builder().id(userId).build())
                .homeComingStatus(HomeComingStatus.NOT_INITIATED)
                .invitationStatus(InvitationStatus.ACCEPTED)
                .build();
    }

    public Runnable getTask() {
        return new NotificationTask();
    }

    private class NotificationTask implements Runnable {
        @Override
        public void run() {
            new GoodBamScheduler().schedule(user.getId(), room.getId(), confirmationType);
        }
    }

}
