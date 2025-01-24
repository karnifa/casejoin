package com.casejoin.project.core.dto;

import com.casejoin.project.infra.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {

    private Long id;
    private LocalDateTime dataCriacao;
    private Double total;
    private List<ProdutoDTO> produtos;

}
