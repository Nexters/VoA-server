package com.voa.goodbam.common.response;

public enum StatusCode {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    UNSUPPORTED_MEDIA_TYPE(415),

    INTERNAL_SERVER_ERROR(500);

    private final int code;

    private StatusCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
