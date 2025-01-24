package com.casejoin.project.core.dto;

import com.casejoin.project.infra.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO() {

    }
}
