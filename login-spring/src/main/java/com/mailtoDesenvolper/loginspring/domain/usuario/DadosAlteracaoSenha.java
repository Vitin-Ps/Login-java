package com.mailtoDesenvolper.loginspring.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAlteracaoSenha(
        @NotBlank
        String senhaAntiga,
        @NotBlank
        String novaSenha,
        @NotBlank
        String confirmaSenha,
        @NotBlank
        String rawToken
) {
}
