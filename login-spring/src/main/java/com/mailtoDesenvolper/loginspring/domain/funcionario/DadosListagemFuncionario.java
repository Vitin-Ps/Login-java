package com.mailtoDesenvolper.loginspring.domain.funcionario;

public record DadosListagemFuncionario(
        Long id,
        String nome,
        Integer idade
) {
    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getIdade());
    }
}
