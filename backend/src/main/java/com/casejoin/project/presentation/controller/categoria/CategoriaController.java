package com.casejoin.project.presentation.controller.categoria;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.infra.entity.Categoria;
import com.casejoin.project.infra.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public final class CategoriaController implements CategoriaAPI{

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public CategoriaDTO criar(@RequestBody CategoriaDTO categoria) {
        return categoriaService.salvar(categoria);
    }

    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CategoriaDTO atualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        return categoriaService.atualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
    }
}