package com.casejoin.project.produto;

import com.casejoin.project.core.dto.ProdutoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void criar_DeveRetornarStatusCreated_QuandoProdutoValido() {
        ProdutoDTO novoProduto = new ProdutoDTO();
        novoProduto.setNome("Produto Teste");
        novoProduto.setPreco(100.0);
        novoProduto.setQuantidade(10);

        ResponseEntity<ProdutoDTO> response = restTemplate.postForEntity("/api/produtos?categoriaId=1", novoProduto, ProdutoDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto Teste", response.getBody().getNome());
    }

    @Test
    void listar_DeveRetornarListaDeProdutos() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/produtos", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    void buscarPorId_DeveRetornarProduto_QuandoIdValido() {
        ResponseEntity<ProdutoDTO> response = restTemplate.getForEntity("/api/produtos/1", ProdutoDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto Teste", response.getBody().getNome());
    }

    @Test
    void buscarPorId_DeveRetornarStatusNotFound_QuandoIdInvalido() {
        ResponseEntity<ProdutoDTO> response = restTemplate.getForEntity("/api/produtos/999", ProdutoDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void atualizar_DeveAtualizarProduto_QuandoIdValido() {
        ProdutoDTO produtoAtualizado = new ProdutoDTO();
        produtoAtualizado.setNome("Produto Atualizado");
        produtoAtualizado.setPreco(200.0);
        produtoAtualizado.setQuantidade(20);

        HttpEntity<ProdutoDTO> request = new HttpEntity<>(produtoAtualizado);
        ResponseEntity<ProdutoDTO> response = restTemplate.exchange("/api/produtos/1", HttpMethod.PUT, request, ProdutoDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto Atualizado", response.getBody().getNome());
    }

    @Test
    void atualizar_DeveRetornarStatusNotFound_QuandoIdInvalido() {
        ProdutoDTO produtoAtualizado = new ProdutoDTO();
        produtoAtualizado.setNome("Produto Atualizado");

        HttpEntity<ProdutoDTO> request = new HttpEntity<>(produtoAtualizado);
        ResponseEntity<ProdutoDTO> response = restTemplate.exchange("/api/produtos/999", HttpMethod.PUT, request, ProdutoDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deletar_DeveRemoverProduto_QuandoIdValido() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/produtos/1", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deletar_DeveRetornarStatusNotFound_QuandoIdInvalido() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/produtos/999", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
