package com.voa.goodbam.repository;

import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatusInRoomRepository extends CrudRepository<UserStatusInRoom, Long> {
    List<UserStatusInRoom> findByRoomId(Long roomId);
    /**
     *
     * TODO
     * updateRoomInvitationStatus(roomId, userId, invitationstatus)
     * updateHomeComingStatus(roomId, userId, homecomingstatus)
     *
     */

}
