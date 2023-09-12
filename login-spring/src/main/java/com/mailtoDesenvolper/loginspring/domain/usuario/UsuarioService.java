package com.mailtoDesenvolper.loginspring.domain.usuario;

import com.mailtoDesenvolper.loginspring.domain.token.TokenTransparenteService;
import com.mailtoDesenvolper.loginspring.repositories.UsuarioRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private TokenTransparenteService tokenTransparenteService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @SneakyThrows
    public void alterarSenha(DadosAlteracaoSenha dados) {
        var publicData = tokenTransparenteService.readPublicData(dados.rawToken());

        if(tokenTransparenteService.tempoExpirado(publicData)) throw new RuntimeException("Token Expirado");

        var usuario = usuarioRepository.findByLoginTwo(publicData.login());
        if(usuario == null) throw new RuntimeException("Usuario não encontrado");


        var tokenService = tokenTransparenteService.getInstanceFor(usuario);
        tokenService.verifyToken(dados.rawToken());
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(publicData.login(), dados.senhaAntiga());
        if(!dados.novaSenha().equals(dados.confirmaSenha())) throw new RuntimeException("Senhas não são iguais.");

        var novaSenhaCodificada = new BCryptPasswordEncoder().encode(dados.novaSenha());
        usuario.setSenha(novaSenhaCodificada);
        usuarioRepository.save(usuario);
    }
}

