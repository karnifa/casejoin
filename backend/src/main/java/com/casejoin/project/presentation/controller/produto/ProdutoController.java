package com.casejoin.project.presentation.controller.produto;

import com.casejoin.project.core.dto.ProdutoDTO;
import com.casejoin.project.infra.entity.Produto;
import com.casejoin.project.infra.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public non-sealed class ProdutoController implements ProdutoAPI{

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoDTO criar(@RequestBody ProdutoDTO produto, @RequestParam Long categoriaId) {
        return produtoService.salvar(produto, categoriaId);
    }

    @GetMapping
    public List<ProdutoDTO> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}