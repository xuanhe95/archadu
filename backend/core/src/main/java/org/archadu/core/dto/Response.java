package org.archadu.core.dto;

public class Response<T> {
    private int status;
    private String message;
    private T data;

    public Response() {}

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Convenience static methods for creating responses
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "Success", data);
    }

    public static <T> Response<T> error(String message, T data) {
        return new Response<>(500, message, data);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(500, message, null);
    }
}
