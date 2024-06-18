package org.archadu.core.util;

import org.archadu.core.dto.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response<String> handleGlobalException(Exception e, WebRequest request) {
        Response<String> response = new Response<>(false, "Internal server error", e.getMessage());
        return response;
    }
}
