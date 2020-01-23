package com.voa.goodbam.domain.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "room")
    private List<UserStatusInRoom> users;

    public static Room create(String name) {
        return new Room(-1, name, generateUniqueCode(), null);
    }

    private static String generateUniqueCode() {

        return "";
    }

    private void sendLinksToRoomMembers() {

    }

}
