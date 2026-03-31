package com.security.security.model.controller.response;

public record LoginResponseDTO(

    String accessToken,
    Long expiresIn
) {}
