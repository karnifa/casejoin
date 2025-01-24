package com.casejoin.project.infra.repository;

import com.casejoin.project.infra.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNome(String nome);
    Optional<Categoria> findById(Long categoriaId);
}
