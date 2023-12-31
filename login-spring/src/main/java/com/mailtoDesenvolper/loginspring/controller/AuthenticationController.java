package com.mailtoDesenvolper.loginspring.controller;

import com.mailtoDesenvolper.loginspring.domain.usuario.*;
import com.mailtoDesenvolper.loginspring.repositories.UsuarioRepository;
import com.mailtoDesenvolper.loginspring.infra.DadosTokenJWT;
import com.mailtoDesenvolper.loginspring.infra.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autencacao = manager.authenticate(autenticacaoToken);
        var tokenJWT = tokenService.gerarToken((Usuario) autencacao.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        var senhaCodificada = new BCryptPasswordEncoder().encode(dados.senha());
        var usuario = new Usuario(dados.login(), senhaCodificada, dados.tipoUsuario());
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/recuperar")
    @Transactional
    public ResponseEntity recuperarsenha(@RequestBody @Valid DadosAlteracaoSenha dados, String rawToken) {
        try {
            usuarioService.alterarSenha(dados);
        } catch (Exception ex) {
            throw new RuntimeException("erro:" + ex);
        }
        return ResponseEntity.ok("Senha Alterada");
    }
}
