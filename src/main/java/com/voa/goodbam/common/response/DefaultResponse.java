package com.voa.goodbam.common.response;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse<T> {
    private StatusCode statusCode;
    private Message message;
    private T data;

    public DefaultResponse(final StatusCode statusCode, final Message message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public static <T> DefaultResponse<T> of(final StatusCode code, final Message message) {
        return of(code, message, null);
    }

    public static <T> DefaultResponse<T> of(final StatusCode code, final Message message, final T data) {
        return DefaultResponse.<T>builder()
                .statusCode(code)
                .message(message)
                .data(data)
                .build();
    }

}
