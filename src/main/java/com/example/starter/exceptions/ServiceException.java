package com.example.starter.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
    private HttpStatus status;
    private String objeto;
    private String message;

    public ServiceException(HttpStatus status, String objeto, String message) {
        this.status = status;
        this.objeto = objeto;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
