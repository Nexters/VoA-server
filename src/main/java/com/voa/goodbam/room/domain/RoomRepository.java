package com.voa.goodbam.room.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findByUsers_User_Id(Long user_id);
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
