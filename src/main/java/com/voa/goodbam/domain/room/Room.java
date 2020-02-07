package com.voa.goodbam.domain.room;

import com.voa.goodbam.domain.messenger.Messenger;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "room")
    private List<UserStatusInRoom> users;

    @OneToMany(mappedBy = "room")
    private List<Messenger> messages;

    public static Room create(String name) {
        return Room.builder().name(name).code(generateUniqueCode()).build();
    }

    private static String generateUniqueCode() {

        return "";
    }

    private void sendLinksToRoomMembers() {

    }

}
