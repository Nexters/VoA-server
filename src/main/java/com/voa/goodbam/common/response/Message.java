package com.voa.goodbam.common.response;

public enum Message {

    OK("성공"),
    BAD_REQUEST("잘못된 요청"),
    INTERNAL_SEVER_ERROR("서버 내부 에러 발생"),
    DB_ERROR("데이터 베이스 에러 발생");


    private final String message;

    private Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
