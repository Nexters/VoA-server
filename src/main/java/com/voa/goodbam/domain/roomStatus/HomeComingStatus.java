package com.voa.goodbam.domain.roomStatus;

public enum HomeComingStatus {
    // noneStart -> 귀가전, starting -> 귀가 중, end -> 귀가 완료, past -> 귀가 시간 지남,
    NOT_INITIATED("noneStart"), ON_THE_WAY_HOME("starting"), ARRIVED_HOME("end"), NEED_CONFIRMATION("past");

    private final String message;

    private HomeComingStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
