package com.casejoin.project.infra.service;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.infra.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    CategoriaDTO salvar(CategoriaDTO categoria);

    List<CategoriaDTO> listar();

    CategoriaDTO buscarPorId(Long id);

    CategoriaDTO atualizar(Long id, CategoriaDTO categoriaAtualizada);

    void deletar(Long id);
}