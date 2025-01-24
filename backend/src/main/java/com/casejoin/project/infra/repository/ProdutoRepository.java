package com.casejoin.project.infra.repository;

import com.casejoin.project.infra.entity.Categoria;
import com.casejoin.project.infra.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Exemplo de método customizado
    boolean existsByNome(String nome);

}