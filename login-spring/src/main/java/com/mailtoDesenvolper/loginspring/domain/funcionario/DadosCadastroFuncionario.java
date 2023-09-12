package com.mailtoDesenvolper.loginspring.domain.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroFuncionario(
        @NotBlank
        String rawToken,
        @NotBlank
        String nome,
        @NotNull
        Integer idade
) {
}
