package com.voa.goodbam.domain.login;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {
    private int status;
    private String message;
    private T data;

    public DefaultResponse(final int status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static <T> DefaultResponse<T> of(final int code, final String message) {
        return of(code, message, null);
    }

    public static <T> DefaultResponse<T> of(final StatusCode code, final Message message) {
        return of(code.getCode(), message.getMessage(), null);
    }

    public static <T> DefaultResponse<T> of(final StatusCode code, final Message message, final T data) {
        return of(code.getCode(), message.getMessage(), data);
    }

    public static <T> DefaultResponse<T> of(final int code, final String message, final T data) {
        return DefaultResponse.<T>builder()
                .status(code)
                .message(message)
                .data(data)
                .build();
    }

}
