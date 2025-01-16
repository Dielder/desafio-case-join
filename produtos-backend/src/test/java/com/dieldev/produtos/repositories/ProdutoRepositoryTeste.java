package com.dieldev.produtos.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.dieldev.produtos.dto.ProdutoDTO;
import com.dieldev.produtos.model.Produto;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class ProdutoRepositoryTeste {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("Deve retornar o produto do Banco de Dados")
	void findByNomeProdutoSucesso() {
		ProdutoDTO produtoDTO = new ProdutoDTO("Geladeira", 1000.00, 2, "Geladeira brastemp, 2 portas.");
		this.criarProduto(produtoDTO);

		this.produtoRepository.findByNome("Geladeira");
		Optional<Produto> acharProduto = this.produtoRepository.findByNome("Geladeira");

		assertThat(acharProduto.isPresent()).isTrue();
	}

	@Test
	@DisplayName("Deve listar todos os produtos")
	void listarProdutosSucesso() {
		produtoRepository.save(new Produto("Produto A", 200.00, 3, "Descrição A"));
		produtoRepository.save(new Produto("Produto B", 300.00, 5, "Descrição B"));

		List<Produto> produtos = produtoRepository.findAll();

		assertFalse(produtos.isEmpty());
		assertEquals(2, produtos.size());
	}

	private Produto criarProduto(ProdutoDTO produtoDTO) {
		Produto novoProduto = new Produto(produtoDTO);
		this.entityManager.persist(novoProduto);
		return novoProduto;
	}

}
