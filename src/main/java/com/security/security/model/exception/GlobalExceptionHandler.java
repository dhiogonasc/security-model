package com.security.security.model.exception;

import com.security.security.model.controller.response.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleBusinessException(BusinessException ex) {

        List<ErrorResponseDTO> errorDetails = List.of(
                new ErrorResponseDTO(
                        ex.getField(),
                        ex.getMessage()
                )
        );

        return ResponseEntity.status(ex.getStatus()).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<ErrorResponseDTO> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new ErrorResponseDTO(f.getField(), f.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(BAD_REQUEST).body(errors);
    }
}