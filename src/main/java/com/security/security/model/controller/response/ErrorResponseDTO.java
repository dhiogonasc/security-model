package com.security.security.model.controller.response;

public record ErrorResponseDTO(
        String field,
        String error
) {}