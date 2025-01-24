package com.casejoin.project.core.usecase;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.core.dto.ProdutoDTO;
import com.casejoin.project.infra.entity.Categoria;
import com.casejoin.project.infra.entity.Produto;
import com.casejoin.project.infra.repository.CategoriaRepository;
import com.casejoin.project.infra.repository.ProdutoRepository;
import com.casejoin.project.infra.service.CategoriaService;
import com.casejoin.project.infra.service.ProdutoService;
import com.casejoin.project.presentation.mapper.CategoriaMapper;
import com.casejoin.project.presentation.mapper.ProdutoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoUseCase implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final ProdutoMapper produtoMapper;

    public ProdutoUseCase(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoDTO salvar(ProdutoDTO produtoDTO, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + categoriaId + " não encontrada"));

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setCategoria(categoria);
        produtoRepository.save(produto);
        return produtoMapper.toProdutoDTO(produto);
    }

    @Override
    public List<ProdutoDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> {
                    ProdutoDTO produtoDTO = new ProdutoDTO();
                    produtoDTO.setId(produto.getId());
                    produtoDTO.setNome(produto.getNome());
                    produtoDTO.setPreco(produto.getPreco());
                    produtoDTO.setQuantidade(produto.getQuantidade());

                    CategoriaDTO categoriaDTO = new CategoriaDTO();
                    categoriaDTO.setId(produto.getCategoria().getId());
                    categoriaDTO.setNome(produto.getCategoria().getNome());

                    produtoDTO.setCategoria(categoriaDTO);

                    return produtoDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produtoMapper.toProdutoDTO(produto);
    }

    @Override
    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoAtualizado) {
        // Busca o produto no banco de dados
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado"));

        // Atualiza os campos do produto
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());

        // Verifica se a categoria no DTO é válida
        if (produtoAtualizado.getCategoria() != null && produtoAtualizado.getCategoria().getId() != null) {
            Long categoriaId = produtoAtualizado.getCategoria().getId();
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException("Categoria com ID " + categoriaId + " não encontrada"));
            produto.setCategoria(categoria);
        } else {
            throw new RuntimeException("Categoria inválida ou não informada no DTO");
        }

        // Salva as alterações no banco de dados
        Produto produtoAtualizadoNoBanco = produtoRepository.save(produto);

        // Retorna o DTO atualizado
        return produtoMapper.toProdutoDTO(produtoAtualizadoNoBanco);
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}