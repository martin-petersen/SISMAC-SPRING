package com.example.starter.handler;

import com.example.starter.error.ApiError;
import com.example.starter.exceptions.ServiceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerException {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> argumentNotValid(final HttpServletRequest req, final MethodArgumentNotValidException exception) {
        Map<String,String> error = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = ((FieldError) objectError).getDefaultMessage();
            error.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(new ApiError(error, HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ApiError> notFound(final ServiceException e) {
        Map<String,String> error = new HashMap<>();
        error.put(e.getObjeto(),e.getMessage());
        return new ResponseEntity<>(new ApiError(error,e.getStatus(),e.getStatus().value()),e.getStatus());
    }
}
