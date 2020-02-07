package com.voa.goodbam.domain.room.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserInfoResponse {
    Long userID;
    String userName;
    String userStatus;
    String userProfileURL;
    String elapsedTime;
    String totalTime;
    String responseTime;
    Boolean isMessage;
}