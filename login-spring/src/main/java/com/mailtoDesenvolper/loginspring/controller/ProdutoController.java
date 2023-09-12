package com.mailtoDesenvolper.loginspring.controller;

import com.mailtoDesenvolper.loginspring.domain.produto.DadosCadastroProduto;
import com.mailtoDesenvolper.loginspring.domain.produto.DadosListagemProduto;
import com.mailtoDesenvolper.loginspring.domain.produto.Produto;
import com.mailtoDesenvolper.loginspring.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados) {
        var produto = new Produto(dados);
        repository.save(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

}
