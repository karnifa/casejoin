package com.casejoin.project.core.usecase;


import com.casejoin.project.core.dto.PedidoDTO;
import com.casejoin.project.core.dto.PedidoItemDTO;
import com.casejoin.project.infra.entity.Pedido;
import com.casejoin.project.infra.entity.PedidoProduto;
import com.casejoin.project.infra.entity.Produto;
import com.casejoin.project.infra.repository.PedidoRepository;
import com.casejoin.project.infra.repository.ProdutoRepository;
import com.casejoin.project.infra.service.PedidoService;
import com.casejoin.project.presentation.mapper.PedidoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoUseCase implements PedidoService {


    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoUseCase(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoDTO criar(List<PedidoItemDTO> itens) {
        // Cria o objeto Pedido
        Pedido pedido = new Pedido();

        // Criação dos itens do pedido
        List<PedidoProduto> pedidoProdutos = itens.stream().map(item -> {
            // Busca o produto pelo ID
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto com ID " + item.getProdutoId() + " não encontrado"));

            // Cria o item do pedido associando o pedido, produto e quantidade
            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido); // Estabelece a relação com o pedido
            pedidoProduto.setProduto(produto); // Estabelece a relação com o produto
            pedidoProduto.setQuantidade(item.getQuantidade()); // Define a quantidade

            return pedidoProduto;
        }).collect(Collectors.toList());

        // Configura os itens no pedido
        pedido.setItens(pedidoProdutos);

        // Calcula o total do pedido
        pedido.setTotal(calcularTotal(pedidoProdutos));

        // Salva o pedido no banco de dados
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Converte o pedido salvo para DTO e retorna
        return pedidoMapper.toPedidoDTO(pedidoSalvo);
    }


    @Override
    public List<PedidoDTO> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toPedidoDTOList(pedidos);

    }

    @Override
    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com ID " + id + " não encontrado"));
        return pedidoMapper.toPedidoDTO(pedido);
    }

    private Double calcularTotal(List<PedidoProduto> pedidoProdutos) {
        return pedidoProdutos.stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }
    @Override
    public void excluirPedido(Long id) {
        // Verifica se o pedido existe
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com ID " + id + " não encontrado"));

        // Exclui o pedido
        pedidoRepository.delete(pedido);
    }
}