package com.casejoin.project.presentation.mapper;

import com.casejoin.project.core.dto.PedidoDTO;
import com.casejoin.project.infra.entity.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoDTO toPedidoDTO(Pedido pedido);

    List<PedidoDTO> toPedidoDTOList(List<Pedido> pedidos);
}
