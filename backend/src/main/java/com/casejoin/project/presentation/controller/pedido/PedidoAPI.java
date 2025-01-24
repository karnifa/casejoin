package com.casejoin.project.presentation.controller.pedido;

import com.casejoin.project.core.dto.PedidoDTO;
import com.casejoin.project.core.dto.PedidoItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Pedidos", description = "API responsável por gerenciar pedidos.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Parâmetros de entrada inválidos."),
        @ApiResponse(responseCode = "404", description = "O recurso solicitado não foi encontrado."),
        @ApiResponse(responseCode = "409", description = "Conflito ao salvar a entidade."),
        @ApiResponse(responseCode = "422", description = "Entidade não processável."),
        @ApiResponse(responseCode = "500", description = "Ocorreu um erro genérico no servidor."),
        @ApiResponse(responseCode = "503", description = "O serviço solicitado não está disponível.")
})
public sealed interface PedidoAPI permits PedidoController {

    @Operation(summary = "Cria um novo pedido.",
            description = "Adiciona um novo pedido ao sistema com base na lista de itens, incluindo o ID do produto e a quantidade.")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso.")
    PedidoDTO criar(
            @Parameter(description = "Lista de itens do pedido contendo ID do produto e quantidade.", required = true)
            List<PedidoItemDTO> itens);

    @Operation(summary = "Lista todos os pedidos.",
            description = "Retorna uma lista de todos os pedidos cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso.")
    List<PedidoDTO> listar();

    @Operation(summary = "Busca um pedido por ID.",
            description = "Retorna os detalhes de um pedido específico com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Detalhes do pedido retornados com sucesso.")
    PedidoDTO buscarPorId(
            @Parameter(description = "ID do pedido a ser buscado.", required = true)
            Long id);

    @Operation(summary = "Exclui um pedido.",
            description = "Remove um pedido do sistema com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso.")
    void excluirPedido(
            @Parameter(description = "ID do pedido a ser excluído.", required = true)
            Long id);
}

