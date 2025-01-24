package com.casejoin.project.presentation.mapper;

import com.casejoin.project.core.dto.PedidoProdutoDTO;
import com.casejoin.project.infra.entity.PedidoProduto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProdutoMapper.class)
public interface PedidoProdutoMapper {

    PedidoProdutoDTO toPedidoProdutoDTO(PedidoProduto pedidoProduto);

    List<PedidoProdutoDTO> toPedidoProdutoDTOList(List<PedidoProduto> pedidoProdutos);
}
