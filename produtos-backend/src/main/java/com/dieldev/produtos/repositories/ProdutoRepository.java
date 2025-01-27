package com.dieldev.produtos.repositories;

import com.dieldev.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
	// retorna um Optional contendo o produto, se encontrado.
	Optional<Produto> findByNome(String nome);
    // retorna true se existir um produto com o nome especificado e diferente do ID.
    boolean existsByNomeAndIdNot(String nome, UUID id);
}

