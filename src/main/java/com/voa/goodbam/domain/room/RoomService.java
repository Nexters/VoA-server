package com.voa.goodbam.domain.room;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.messenger.Messenger;
import com.voa.goodbam.domain.room.response.RoomInfoResponse;
import com.voa.goodbam.domain.room.response.UserInfoResponse;
import com.voa.goodbam.domain.roomStatus.HomeComingStatus;
import com.voa.goodbam.domain.roomStatus.UserStatusInRoom;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.repository.MessengerRepository;
import com.voa.goodbam.repository.RoomRepository;
import com.voa.goodbam.repository.UserStatusInRoomRepository;
import com.voa.goodbam.support.TimeCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MessengerRepository messengerRepository;
    private final UserStatusInRoomRepository userStatusInRoomRepository;

    public RoomService(RoomRepository roomRepository, MessengerRepository messengerRepository, UserStatusInRoomRepository userStatusInRoomRepository) {
        this.roomRepository = roomRepository;
        this.messengerRepository = messengerRepository;
        this.userStatusInRoomRepository = userStatusInRoomRepository;
    }


    public DefaultResponse getRoomInfoByRoomId(Long roomId, Long userId) {
        List<UserStatusInRoom> userInfos = userStatusInRoomRepository.findByRoomId(roomId);
        if(userInfos.isEmpty()){
            return DefaultResponse.of(StatusCode.NO_CONTENT, Message.NO_CONTENT);
        }

        //내가 보냈고, 응답을 받은 것
        List<Messenger> sendMessages = messengerRepository.findMessengersBySenderIdAndResponseAtIsNotNullOrderByResponseAtDesc(userId);
        //내가 받았고, 응답 안한 것
        List<Messenger> receiveMessages = messengerRepository.findMessengersByTargetIdAndResponseAtIsNull(userId);

        List<UserInfoResponse> participants = new ArrayList<>();
        for(UserStatusInRoom userInfo:userInfos){
            User user = userInfo.getUser();
            long responseTime = sendMessages.stream().filter(target->target.getId()==user.getId()).findFirst()
                    .map(messenger -> { return TimeCalculator.minusFromNowToMinute(messenger.getResponseAt());})
                    .orElse(0L);
            boolean isMessage = receiveMessages.stream().filter(sender->sender.getId()==user.getId()).findFirst()
                    .map(messenger -> {return true;})
                    .orElse(false);
            long totalTime = 0;
            long elapsedTime = 0;
            if(!userInfo.getHomeComingStatus().equals(HomeComingStatus.NOT_INITIATED)){
                totalTime = TimeCalculator.minusToMinute(userInfo.getArrivedAt(), userInfo.getStartedAt());
                elapsedTime = TimeCalculator.minusFromNowToMinute(userInfo.getStartedAt());
            }
            participants.add(UserInfoResponse.builder()
                    .userID(user.getId())
                    .userName(user.getName())
                    .userStatus(userInfo.getHomeComingStatus().name())
                    .userProfileURL(user.getProfileImage())
                    .isMessage(isMessage)
                    .elapsedTime(elapsedTime)
                    .totalTime(totalTime)
                    .responseTime(responseTime)
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
