package com.voa.goodbam.repository;

import com.voa.goodbam.model.domain.room.Room;
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
