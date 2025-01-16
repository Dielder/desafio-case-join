package com.dieldev.produtos.repositories;

import com.dieldev.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Optional<Produto> findByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, UUID id);


}

