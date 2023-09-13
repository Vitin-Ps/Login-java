package com.mailtoDesenvolper.loginspring.controller;

import com.mailtoDesenvolper.loginspring.domain.token.DadosCriacaoToken;
import com.mailtoDesenvolper.loginspring.domain.token.DadosToken;
import com.mailtoDesenvolper.loginspring.domain.token.TokenTransparenteService;
import com.mailtoDesenvolper.loginspring.infra.DadosTokenJWT;
import com.mailtoDesenvolper.loginspring.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("token")
@Slf4j
@RequiredArgsConstructor
public class RawTokenController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private TokenTransparenteService tokenTransparenteService;
    @PostMapping("gerar")
    public ResponseEntity gerarToken (@RequestBody @Valid DadosCriacaoToken dados) {
        var user = usuarioRepository.findByLoginTwo(dados.login());
        System.out.println("chegouS");
        if(user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        var token = tokenTransparenteService.gerarToken(user);
        System.out.println("Token Aqui: " + token);
        return ResponseEntity.ok(new DadosToken(token));
    }

    
}
