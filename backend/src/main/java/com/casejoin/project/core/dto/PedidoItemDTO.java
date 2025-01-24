package com.casejoin.project.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PedidoItemDTO {

    private Long produtoId;
    private Integer quantidade;

    public PedidoItemDTO() {
    }

}
