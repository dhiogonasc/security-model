package com.security.security.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final String field;
    private final HttpStatus status;

    public BusinessException(String field, String message, HttpStatus status) {
        super(message);
        this.field = field;
        this.status = status;
    }
}