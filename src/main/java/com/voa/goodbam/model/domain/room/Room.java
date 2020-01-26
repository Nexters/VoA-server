package com.voa.goodbam.model.domain.room;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "room")
    private List<UserStatusInRoom> users;

    public static Room create(String name) {
        return Room.builder().name(name).code(generateUniqueCode()).build();
    }

    private static String generateUniqueCode() {

        return "";
    }

    private void sendLinksToRoomMembers() {

    }

}
