package com.casejoin.project.presentation.controller.categoria;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.core.dto.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
@Tag(name = "Categorias", description = "API responsável por gerenciar categorias.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Parâmetros de entrada inválidos."),
        @ApiResponse(responseCode = "404", description = "O recurso solicitado não foi encontrado."),
        @ApiResponse(responseCode = "409", description = "Conflito ao salvar a entidade."),
        @ApiResponse(responseCode = "422", description = "Entidade não processável."),
        @ApiResponse(responseCode = "500", description = "Ocorreu um erro genérico no servidor."),
        @ApiResponse(responseCode = "503", description = "O serviço solicitado não está disponível.")
})
public sealed interface CategoriaAPI permits CategoriaController {

    @Operation(summary = "Cria uma nova categoria.", description = "Adiciona uma nova categoria ao sistema.")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso.")
    CategoriaDTO criar(
            @Parameter(description = "Dados da categoria a ser criada.", required = true)
            CategoriaDTO categoria);

    @Operation(summary = "Lista todas as categorias.", description = "Retorna uma lista de todas as categorias cadastradas no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso.")
    List<CategoriaDTO> listar();

    @Operation(summary = "Busca uma categoria por ID.", description = "Retorna os detalhes de uma categoria específica com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Detalhes da categoria retornados com sucesso.")
    CategoriaDTO buscarPorId(
            @Parameter(description = "ID da categoria a ser buscada.", required = true)
            Long id);

    @Operation(summary = "Atualiza uma categoria.", description = "Atualiza os dados de uma categoria existente com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso.")
    CategoriaDTO atualizar(
            @Parameter(description = "ID da categoria a ser atualizada.", required = true)
            Long id,
            @Parameter(description = "Novos dados da categoria.", required = true)
            CategoriaDTO categoria);

    @Operation(summary = "Deleta uma categoria.", description = "Remove uma categoria do sistema com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso.")
    void deletar(
            @Parameter(description = "ID da categoria a ser deletada.", required = true)
            Long id);
}
