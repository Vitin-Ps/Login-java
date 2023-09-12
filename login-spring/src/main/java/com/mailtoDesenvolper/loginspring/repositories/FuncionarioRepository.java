package com.mailtoDesenvolper.loginspring.repositories;

import com.mailtoDesenvolper.loginspring.domain.funcionario.Funcionario;
import com.mailtoDesenvolper.loginspring.domain.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Page<Funcionario> findAll(Pageable pageable);
}
