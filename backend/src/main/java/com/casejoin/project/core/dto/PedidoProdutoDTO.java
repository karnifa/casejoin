package com.casejoin.project.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class PedidoProdutoDTO {

    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private Double preco;

    public PedidoProdutoDTO() {
    }

}
