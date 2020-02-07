package com.voa.goodbam.domain.room.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinResponse {
    Boolean isSuccess;
    long roomID;
}
