package com.voa.goodbam.domain.login;

public enum StatusCode {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    UNSUPPORTED_MEDIA_TYPE(415),

    INTERNAL_SERVER_ERROR(500),

    //1001 들어가기 성공 , 1002 : 방이 없어진 상태(24시간 지나서), 1003: 들어갈수없는 방(DB에는 있지만 들어갈 수없는 상태, 방을 폐쇄한 상태), 1004 이미 들어가진상태
    ROOM_JOIN_SUCCESS(1001),
    ROOM_DESTROYED(1002),
    ROOM_BANNED(1003),
    ROOM_ALREADY_JOIN(1004);

    private final int code;

    private StatusCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
