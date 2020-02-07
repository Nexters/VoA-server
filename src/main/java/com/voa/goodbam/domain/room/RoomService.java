package com.voa.goodbam.domain.room;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserStatusInRoomRepository userStatusInRoomRepository;

    public RoomService(RoomRepository roomRepository, UserStatusInRoomRepository userStatusInRoomRepository) {
        this.roomRepository = roomRepository;
        this.userStatusInRoomRepository = userStatusInRoomRepository;
    }

    public DefaultResponse getRoomInfoByRoomId(@PathVariable Long roomId) {
        List<UserStatusInRoom> userInfos = userStatusInRoomRepository.findByRoomId(roomId);
        if(userInfos.isEmpty()){
            return DefaultResponse.of(StatusCode.NO_CONTENT, Message.NO_CONTENT);
        }
        List<UserInfoResponse> participants = new ArrayList<>();
        for(UserStatusInRoom userInfo:userInfos){
            User user = userInfo.getUser();
            participants.add(UserInfoResponse.builder()
                    .userID(user.getId())
                    .userName(user.getName())
                    .userStatus(userInfo.getUserState().name())
                    .userProfileURL(user.getProfileImage())
                    //TODO: Messaging구현 후 수정 필요
                    //isMessage -> true(응답하기 버튼 노출), false(찔러보기 노출),
                    //elapsedTime -> 내가 귀가 시간하고 지난 시간(분)
                    //totalTime -> 내가 귀가 하기로 한 시간(분)
                    //responseTime -> 나에게 응답한 시간
                    .isMessage(false)
                    .elapsedTime("")
                    .totalTime("")
                    .responseTime("")
                    .build());
        }
        RoomInfoResponse roomInfoResponse = RoomInfoResponse.builder()
                .roomID(roomId)
                .roomTitle(userInfos.get(0).getRoom().getName())
                .participants(participants)
                .build();
        return DefaultResponse.of(StatusCode.OK, Message.OK, roomInfoResponse);
    }


}
