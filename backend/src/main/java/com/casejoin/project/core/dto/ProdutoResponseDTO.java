package com.casejoin.project.core.dto;

import com.casejoin.project.infra.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;

}
