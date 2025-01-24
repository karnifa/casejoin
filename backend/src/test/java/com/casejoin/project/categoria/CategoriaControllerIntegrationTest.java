package com.casejoin.project.categoria;

import com.casejoin.project.core.dto.CategoriaDTO;
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
class CategoriaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void criar_DeveRetornarStatusCreated_QuandoCategoriaValida() {
        CategoriaDTO novaCategoria = new CategoriaDTO(null, "Categoria Teste");

        ResponseEntity<CategoriaDTO> response = restTemplate.postForEntity("/api/categorias", novaCategoria, CategoriaDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Categoria Teste", response.getBody().getNome());
    }

    @Test
    void listar_DeveRetornarListaDeCategorias() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/categorias", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    void buscarPorId_DeveRetornarCategoria_QuandoIdExistir() {
        ResponseEntity<CategoriaDTO> response = restTemplate.getForEntity("/api/categorias/1", CategoriaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Categoria Teste", response.getBody().getNome());
    }

    @Test
    void buscarPorId_DeveRetornarStatusNotFound_QuandoIdNaoExistir() {
        ResponseEntity<CategoriaDTO> response = restTemplate.getForEntity("/api/categorias/999", CategoriaDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void atualizar_DeveAtualizarCategoria_QuandoIdExistir() {
        CategoriaDTO categoriaAtualizada = new CategoriaDTO(null, "Categoria Atualizada");
        HttpEntity<CategoriaDTO> request = new HttpEntity<>(categoriaAtualizada);

        ResponseEntity<CategoriaDTO> response = restTemplate.exchange("/api/categorias/1", HttpMethod.PUT, request, CategoriaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Categoria Atualizada", response.getBody().getNome());
    }

    @Test
    void atualizar_DeveRetornarStatusNotFound_QuandoIdNaoExistir() {
        CategoriaDTO categoriaAtualizada = new CategoriaDTO(null, "Categoria Atualizada");
        HttpEntity<CategoriaDTO> request = new HttpEntity<>(categoriaAtualizada);

        ResponseEntity<CategoriaDTO> response = restTemplate.exchange("/api/categorias/999", HttpMethod.PUT, request, CategoriaDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deletar_DeveRemoverCategoria_QuandoIdExistir() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/categorias/1", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deletar_DeveRetornarStatusNotFound_QuandoIdNaoExistir() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/categorias/999", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
