package org.archadu.core.dto;

public class Response <T>{
    private boolean success;
    private String message;
    private T data;

    public Response() {
    }

    public Response(String message){
        this.success = false;
        this.message = message;
    }


    public Response(String message, T data){
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

}
