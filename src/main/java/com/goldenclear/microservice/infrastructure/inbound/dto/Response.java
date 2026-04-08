package com.goldenclear.microservice.infrastructure.inbound.dto;

import java.time.LocalDateTime;

public class Response<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> Response<T> success(T data, String message) {
        return new Response<>(true, message, data);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(false, message, null);
    }

    public static <T> Response<T> empty(String message) {
        return new Response<>(true, message, null);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public LocalDateTime getTimestamp() { return timestamp; }

}
