package com.casejoin.project.infra.service;

import com.casejoin.project.core.dto.PedidoDTO;
import com.casejoin.project.core.dto.PedidoItemDTO;

import java.util.List;

public interface PedidoService {
    PedidoDTO criar(List<PedidoItemDTO> itens);

    List<PedidoDTO> listar();

    PedidoDTO buscarPorId(Long id);

    void excluirPedido(Long id);
}