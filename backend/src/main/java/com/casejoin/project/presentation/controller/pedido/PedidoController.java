package com.casejoin.project.presentation.controller.pedido;


import com.casejoin.project.core.dto.PedidoDTO;
import com.casejoin.project.core.dto.PedidoItemDTO;
import com.casejoin.project.infra.entity.Pedido;
import com.casejoin.project.infra.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public non-sealed class PedidoController implements PedidoAPI {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public PedidoDTO criar(@RequestBody List<PedidoItemDTO> itens) {
        return pedidoService.criar(itens);
    }

    @GetMapping
    public List<PedidoDTO> listar() {
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
    }
}