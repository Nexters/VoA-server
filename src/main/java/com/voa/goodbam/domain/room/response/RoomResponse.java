package com.voa.goodbam.domain.room.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomResponse {
    Long roomId;
    String roomTitle;
}
