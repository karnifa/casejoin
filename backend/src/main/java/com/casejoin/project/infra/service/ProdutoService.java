package com.casejoin.project.infra.service;

import com.casejoin.project.core.dto.ProdutoDTO;
import java.util.List;

public interface ProdutoService {

    ProdutoDTO salvar(ProdutoDTO produto, Long categoriaId);

    List<ProdutoDTO> listar();

    ProdutoDTO buscarPorId(Long id);

    ProdutoDTO atualizar(Long id, ProdutoDTO produtoAtualizado);

    void deletar(Long id);
}
