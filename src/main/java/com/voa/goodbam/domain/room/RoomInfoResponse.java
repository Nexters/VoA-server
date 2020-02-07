package com.voa.goodbam.domain.room;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RoomInfoResponse {
    Long roomID;
    String roomTitle;
    List<UserInfoResponse> participants;
}
