package com.mailtoDesenvolper.loginspring.domain.funcionario;

import com.mailtoDesenvolper.loginspring.domain.produto.DadosCadastroProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "funcionario")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.idade = dados.idade();
    }
}
