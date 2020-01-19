package com.voa.goodbam.domain.room;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "room")
    private List<UserStatusInRoom> users;

    private String generateUniqueCode() {

        return "";
    }

}
