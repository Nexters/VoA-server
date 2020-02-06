package com.voa.goodbam.room.dto;

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
