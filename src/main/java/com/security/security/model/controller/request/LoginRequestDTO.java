package com.security.security.model.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "Email obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "Senha obrigatória")
        String password
) {}
