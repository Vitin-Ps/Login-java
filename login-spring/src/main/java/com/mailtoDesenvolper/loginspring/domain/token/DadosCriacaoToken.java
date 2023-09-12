package com.mailtoDesenvolper.loginspring.domain.token;

import jakarta.validation.constraints.NotBlank;

public record DadosCriacaoToken(
        @NotBlank
        String login
) {
}
