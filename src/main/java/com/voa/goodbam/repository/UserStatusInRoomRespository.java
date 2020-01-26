package com.voa.goodbam.repository;

import com.voa.goodbam.model.domain.room.UserStatusInRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusInRoomRespository extends CrudRepository<UserStatusInRoom, Long> {

    /**
     *
     * TODO
     * updateRoomInvitationStatus(roomId, userId, invitationstatus)
     * updateHomeComingStatus(roomId, userId, homecomingstatus)
     *
     */

}
