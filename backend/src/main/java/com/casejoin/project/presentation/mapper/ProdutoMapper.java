package com.casejoin.project.presentation.mapper;

import com.casejoin.project.core.dto.ProdutoDTO;
import com.casejoin.project.infra.entity.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toProduto(ProdutoDTO produtoDTO);
    ProdutoDTO toProdutoDTO(Produto produto);

    List<ProdutoDTO> toProdutoDTOList(List<Produto> all);
}
