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
    Long elapsedTime;
    Long totalTime;
    Long responseTime;
    Boolean isMessage;
    //isMessage -> true(응답하기 버튼 노출), false(찔러보기 노출),
    //elapsedTime -> 내가 귀가 시간하고 지난 시간(분)
    //totalTime -> 내가 귀가 하기로 한 시간(분)
    //responseTime -> 나에게 응답한 시간
}