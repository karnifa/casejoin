package com.casejoin.project.core.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
    private CategoriaDTO categoria;

    public ProdutoDTO() {

    }
}
