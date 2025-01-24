package com.casejoin.project.presentation.controller.produto;

import com.casejoin.project.core.dto.ProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Produtos", description = "API responsável por gerenciar produtos.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Parâmetros de entrada inválidos."),
        @ApiResponse(responseCode = "404", description = "O recurso solicitado não foi encontrado."),
        @ApiResponse(responseCode = "409", description = "Conflito ao salvar a entidade."),
        @ApiResponse(responseCode = "422", description = "Entidade não processável."),
        @ApiResponse(responseCode = "500", description = "Ocorreu um erro genérico no servidor."),
        @ApiResponse(responseCode = "503", description = "O serviço solicitado não está disponível.")
})
public sealed interface ProdutoAPI permits ProdutoController {

    @Operation(summary = "Cria um novo produto.", description = "Adiciona um novo produto ao sistema, associando-o a uma categoria específica.")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso.")
    ProdutoDTO criar(
            @Parameter(description = "Dados do produto a ser criado.", required = true)
            ProdutoDTO produto,
            @Parameter(description = "ID da categoria à qual o produto será associado.", required = true)
            Long categoriaId);

    @Operation(summary = "Lista todos os produtos.", description = "Retorna uma lista de todos os produtos cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso.")
    List<ProdutoDTO> listar();

    @Operation(summary = "Busca um produto por ID.", description = "Retorna os detalhes de um produto específico com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Detalhes do produto retornados com sucesso.")
    ProdutoDTO buscarPorId(
            @Parameter(description = "ID do produto a ser buscado.", required = true)
            Long id);

    @Operation(summary = "Atualiza um produto.", description = "Atualiza os dados de um produto existente com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso.")
    ProdutoDTO atualizar(
            @Parameter(description = "ID do produto a ser atualizado.", required = true)
            Long id,
            @Parameter(description = "Novos dados do produto.", required = true)
            ProdutoDTO produto);

    @Operation(summary = "Deleta um produto.", description = "Remove um produto do sistema com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso.")
    void deletar(
            @Parameter(description = "ID do produto a ser deletado.", required = true)
            Long id);
}

