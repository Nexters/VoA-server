package com.voa.goodbam.repository;

import com.voa.goodbam.domain.room.UserStatusInRoom;
import org.springframework.data.repository.CrudRepository;

public interface UserStatusInRoomRespository extends CrudRepository<UserStatusInRoom, Long> {

    /**
     *
     * TODO
     * updateRoomInvitationStatus(roomId, userId, invitationstatus)
     * updateHomeComingStatus(roomId, userId, homecomingstatus)
     *
     */

}
