package com.security.security.model.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Nome completo obrigatório")
        String fullName,

        @NotBlank(message = "Email obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatório")
        String password
) {}
