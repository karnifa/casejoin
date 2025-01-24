package com.casejoin.project.presentation.mapper;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.infra.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO toCategoriaDTO(Categoria categoria);

    List<CategoriaDTO> toCategoriaDTOList(List<Categoria> all);
}
