package com.casejoin.project.categoria;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.core.usecase.CategoriaUseCase;
import com.casejoin.project.infra.entity.Categoria;
import com.casejoin.project.infra.repository.CategoriaRepository;
import com.casejoin.project.presentation.mapper.CategoriaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaUseCaseTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaUseCase categoriaUseCase;

    @Test
    void salvar_DeveSalvarNovaCategoria_QuandoNomeNaoExiste() {
        CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Categoria Teste");
        Categoria categoria = new Categoria(1L, "Categoria Teste");

        when(categoriaMapper.toCategoria(categoriaDTO)).thenReturn(categoria);
        when(categoriaRepository.existsByNome(categoria.getNome())).thenReturn(false);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toCategoriaDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO resultado = categoriaUseCase.salvar(categoriaDTO);

        assertEquals(categoriaDTO, resultado);
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void salvar_DeveLancarExcecao_QuandoNomeJaExiste() {
        CategoriaDTO categoriaDTO = new CategoriaDTO(null, "Categoria Existente");
        Categoria categoria = new Categoria(null, "Categoria Existente");

        when(categoriaMapper.toCategoria(categoriaDTO)).thenReturn(categoria);
        when(categoriaRepository.existsByNome(categoria.getNome())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> categoriaUseCase.salvar(categoriaDTO));
        verify(categoriaRepository, never()).save(any());
    }

    @Test
    void listar_DeveRetornarListaDeCategorias_QuandoExistemCategorias() {
        List<Categoria> categorias = List.of(new Categoria(1L, "Categoria 1"), new Categoria(2L, "Categoria 2"));
        List<CategoriaDTO> categoriaDTOs = List.of(new CategoriaDTO(1L, "Categoria 1"), new CategoriaDTO(2L, "Categoria 2"));

        when(categoriaRepository.findAll()).thenReturn(categorias);
        when(categoriaMapper.toCategoriaDTOList(categorias)).thenReturn(categoriaDTOs);

        List<CategoriaDTO> resultado = categoriaUseCase.listar();

        assertEquals(categoriaDTOs, resultado);
        verify(categoriaRepository).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarCategoria_QuandoIdExistir() {
        Categoria categoria = new Categoria(1L, "Categoria Teste");
        CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Categoria Teste");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toCategoriaDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO resultado = categoriaUseCase.buscarPorId(1L);

        assertEquals(categoriaDTO, resultado);
        verify(categoriaRepository).findById(1L);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExistir() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoriaUseCase.buscarPorId(1L));
        verify(categoriaRepository).findById(1L);
    }

    @Test
    void atualizar_DeveAtualizarCategoria_QuandoIdExistir() {
        Categoria categoria = new Categoria(1L, "Categoria Original");
        CategoriaDTO categoriaAtualizada = new CategoriaDTO(null, "Categoria Atualizada");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toCategoriaDTO(categoria)).thenReturn(new CategoriaDTO(1L, "Categoria Atualizada"));

        CategoriaDTO resultado = categoriaUseCase.atualizar(1L, categoriaAtualizada);

        assertEquals("Categoria Atualizada", resultado.getNome());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdNaoExistir() {
        CategoriaDTO categoriaAtualizada = new CategoriaDTO(null, "Categoria Atualizada");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoriaUseCase.atualizar(1L, categoriaAtualizada));
        verify(categoriaRepository, never()).save(any());
    }

    @Test
    void deletar_DeveRemoverCategoria_QuandoIdExistir() {
        doNothing().when(categoriaRepository).deleteById(1L);

        categoriaUseCase.deletar(1L);

        verify(categoriaRepository).deleteById(1L);
    }
}
