package com.voa.goodbam.room.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    /**
     * Todo
     *
     * GetRoomsByUser(User)
     * AddUserToRoom(User)
     * DeleteUserFromRoom(User)
     * GetRoomsByUser(User)
     *
     */

}
