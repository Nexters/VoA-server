package com.voa.goodbam.domain.login;

public enum Message {

    OK("성공"),
    BAD_REQUEST("잘못된 요청"),
    INTERNAL_SEVER_ERROR("서버 내부 에러 발생"),
    DB_ERROR("데이터 베이스 에러 발생"),

    AUTH_FAIL("인증 실패"),
    AUTH_SUCCESS("인증 성공"),
    LOGIN_SUCCESS("로그인 성공"),
    LOGIN_FAIL("로그인 실패"),
    NO_CONTENT("데이터 없음"),

    ROOM_JOIN_SUCCESS("방 참여 성공"),
    ROOM_DESTROYED("삭제된 방 참여 실패"),
    ROOM_ALREADY_JOIN("이미 참여된 방입니다.");

    private final String message;

    private Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
