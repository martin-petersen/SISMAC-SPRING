package com.example.starter.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {
    private LocalDateTime timestamp;
    private Map<String,String> error;
    private int statusCode;
    private HttpStatus httpStatus;

    public ApiError(Map<String, String> error, HttpStatus httpStatus, int statusCode) {
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
