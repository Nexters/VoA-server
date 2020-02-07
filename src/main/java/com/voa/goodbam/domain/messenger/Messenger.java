package com.voa.goodbam.domain.messenger;

import com.voa.goodbam.domain.room.Room;
import com.voa.goodbam.domain.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Messenger")
@Getter
@Setter
@Builder
@Data
public class Messenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private User target;

    @CreationTimestamp
    private LocalDateTime receiveAt;
    private LocalDateTime responseAt;

    public static Messenger create(Long senderUserId, Long targetUserId, Long roomId) {
        return Messenger.builder()
                .sender(User.builder().id(senderUserId).build())
                .target(User.builder().id(targetUserId).build())
                .room(Room.builder().id(roomId).build())
                .build();
    }
}