package com.voa.goodbam.repository;

import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusInRoomRepository extends CrudRepository<UserStatusInRoom, Long> {

    /**
     *
     * TODO
     * updateRoomInvitationStatus(roomId, userId, invitationstatus)
     * updateHomeComingStatus(roomId, userId, homecomingstatus)
     *
     */

}
