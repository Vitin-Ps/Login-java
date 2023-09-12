package com.mailtoDesenvolper.loginspring.domain.produto;

import java.math.BigDecimal;

public record DadosListagemProduto(
        Long id,
        String nome,
        BigDecimal preco

) {
    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
