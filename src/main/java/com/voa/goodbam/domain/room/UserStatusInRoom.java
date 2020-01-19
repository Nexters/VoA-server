package com.voa.goodbam.domain.room;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Entity
public class UserStatusInRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private HomeComingStatus userState;
    private InvitationStatus invitationStatus;
    private int minutesToHome;

}
