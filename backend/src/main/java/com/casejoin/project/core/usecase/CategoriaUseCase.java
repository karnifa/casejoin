package com.casejoin.project.core.usecase;

import com.casejoin.project.core.dto.CategoriaDTO;
import com.casejoin.project.infra.entity.Categoria;
import com.casejoin.project.infra.repository.CategoriaRepository;
import com.casejoin.project.infra.service.CategoriaService;
import com.casejoin.project.presentation.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaUseCase implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toCategoria(categoriaDTO);
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new IllegalArgumentException("Categoria com o nome '" + categoria.getNome() + "' já existe.");
        }
        categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaDTO(categoria);
    }

    @Override
    public List<CategoriaDTO> listar() {
        return categoriaMapper.toCategoriaDTOList(categoriaRepository.findAll());
    }

    @Override
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoriaMapper.toCategoriaDTO(categoria);
    }

    @Override
    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaAtualizada) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (categoriaAtualizada.getNome() != null) {
            categoria.setNome(categoriaAtualizada.getNome());
        }
        categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaDTO(categoria);
    }

    @Override
    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }
}