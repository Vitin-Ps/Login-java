package com.mailtoDesenvolper.loginspring.controller;

import com.mailtoDesenvolper.loginspring.domain.funcionario.DadosCadastroFuncionario;
import com.mailtoDesenvolper.loginspring.domain.funcionario.DadosListagemFuncionario;
import com.mailtoDesenvolper.loginspring.domain.funcionario.Funcionario;
import com.mailtoDesenvolper.loginspring.domain.token.TokenTransparenteService;
import com.mailtoDesenvolper.loginspring.domain.usuario.UsuarioService;
import com.mailtoDesenvolper.loginspring.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private TokenTransparenteService tokenTransparenteService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados) {
        try {
            tokenTransparenteService.validaToken(dados.rawToken());
            var funcionario = new Funcionario(dados);
            repository.save(funcionario);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw  new RuntimeException("Erro ao checar Token: " + ex);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> listar(Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosListagemFuncionario::new);
        return ResponseEntity.ok(page);
    }

}
