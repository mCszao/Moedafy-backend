package com.moedafy.api.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegisterDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @Email
        String email,
        @NotBlank
        String name

) {
}
