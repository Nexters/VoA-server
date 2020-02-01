package com.voa.goodbam.status.domain;

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
