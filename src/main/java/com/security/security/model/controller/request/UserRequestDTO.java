package com.security.security.model.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank(message = "Nome completo obrigatório")
        String fullName,

        @NotBlank(message = "Email obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatório")
        @Min(value = 6, message = "Deve possuir ao mínimo 6 caracteres")
        String password
) {}
