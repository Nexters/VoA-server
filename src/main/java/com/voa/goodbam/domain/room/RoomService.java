package com.voa.goodbam.domain.room;

import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserStatusInRoomRepository userStatusInRoomRepository;


}
