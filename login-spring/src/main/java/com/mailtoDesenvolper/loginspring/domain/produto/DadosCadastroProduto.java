package com.mailtoDesenvolper.loginspring.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        @NotBlank
        String nome,
        @NotNull
        BigDecimal preco
) {
}
