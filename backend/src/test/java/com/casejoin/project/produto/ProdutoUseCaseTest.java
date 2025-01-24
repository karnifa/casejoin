package com.casejoin.project.produto;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.core.dto.ProdutoDTO;
import com.casejoin.project.core.usecase.ProdutoUseCase;
import com.casejoin.project.infra.entity.Categoria;
import com.casejoin.project.infra.entity.Produto;
import com.casejoin.project.infra.repository.CategoriaRepository;
import com.casejoin.project.infra.repository.ProdutoRepository;
import com.casejoin.project.presentation.mapper.CategoriaMapper;
import com.casejoin.project.presentation.mapper.ProdutoMapper;
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
class ProdutoUseCaseTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoUseCase produtoUseCase;

    @Test
    void salvar_DeveSalvarNovoProduto_QuandoCategoriaIdValido() {
        Categoria categoria = new Categoria(1L, "Categoria Teste");
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setPreco(100.0);
        produto.setQuantidade(10);
        produto.setCategoria(categoria);

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setPreco(100.0);
        produtoDTO.setQuantidade(10);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toProdutoDTO(any(Produto.class))).thenReturn(produtoDTO);

        ProdutoDTO resultado = produtoUseCase.salvar(produtoDTO, 1L);

        assertEquals(produtoDTO.getNome(), resultado.getNome());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void salvar_DeveLancarExcecao_QuandoCategoriaIdInvalido() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setPreco(100.0);
        produtoDTO.setQuantidade(10);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> produtoUseCase.salvar(produtoDTO, 1L));
        verify(produtoRepository, never()).save(any());
    }

    @Test
    void listar_DeveRetornarListaDeProdutos() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(100.0);
        produto.setQuantidade(10);

        List<Produto> produtos = List.of(produto);

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setPreco(100.0);
        produtoDTO.setQuantidade(10);

        when(produtoRepository.findAll()).thenReturn(produtos);
        when(produtoMapper.toProdutoDTO(any(Produto.class))).thenReturn(produtoDTO);

        List<ProdutoDTO> resultado = produtoUseCase.listar();

        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getNome());
        verify(produtoRepository).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarProduto_QuandoIdValido() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Produto Teste");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoMapper.toProdutoDTO(produto)).thenReturn(produtoDTO);

        ProdutoDTO resultado = produtoUseCase.buscarPorId(1L);

        assertEquals("Produto Teste", resultado.getNome());
        verify(produtoRepository).findById(1L);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdInvalido() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> produtoUseCase.buscarPorId(1L));
        verify(produtoRepository).findById(1L);
    }

    @Test
    void atualizar_DeveAtualizarProduto_QuandoIdValido() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Original");

        ProdutoDTO produtoAtualizado = new ProdutoDTO();
        produtoAtualizado.setNome("Produto Atualizado");
        produtoAtualizado.setPreco(200.0);
        produtoAtualizado.setQuantidade(20);

        Categoria categoria = new Categoria(1L, "Categoria Atualizada");
        produtoAtualizado.setCategoria(new CategoriaDTO(1L, "Categoria Atualizada"));

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(produtoRepository.save(produto)).thenReturn(produto);
        when(produtoMapper.toProdutoDTO(produto)).thenReturn(produtoAtualizado);

        ProdutoDTO resultado = produtoUseCase.atualizar(1L, produtoAtualizado);

        assertEquals("Produto Atualizado", resultado.getNome());
        verify(produtoRepository).save(produto);
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdInvalido() {
        ProdutoDTO produtoAtualizado = new ProdutoDTO();
        produtoAtualizado.setNome("Produto Atualizado");

        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> produtoUseCase.atualizar(1L, produtoAtualizado));
        verify(produtoRepository, never()).save(any());
    }

    @Test
    void deletar_DeveRemoverProduto_QuandoIdValido() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoUseCase.deletar(1L);

        verify(produtoRepository).deleteById(1L);
    }
}
