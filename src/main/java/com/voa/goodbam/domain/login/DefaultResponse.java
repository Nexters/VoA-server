package com.voa.goodbam.domain.login;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
