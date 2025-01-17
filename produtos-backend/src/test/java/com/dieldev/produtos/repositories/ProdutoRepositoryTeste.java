package com.dieldev.produtos.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		System.out.println("\n=== Iniciando teste: findByNomeProdutoSucesso ===");

		// Criar e persistir produto
		ProdutoDTO produtoDTO = new ProdutoDTO("Geladeira", 1000.00, 2, "Geladeira brastemp, 2 portas.");
		Produto produtoCriado = this.criarProduto(produtoDTO);
		System.out.println("Produto criado: " + produtoCriado);

		// Buscar produto por nome
		Optional<Produto> acharProduto = this.produtoRepository.findByNome("Geladeira");
		System.out.println("Produto encontrado: " + acharProduto.orElse(null));

		// Verificar resultados
		assertThat(acharProduto.isPresent()).isTrue();

		System.out.println("=== Fim do teste: findByNomeProdutoSucesso ===\n");
	}

	@Test
	@DisplayName("Deve listar todos os produtos")
	void listarProdutosSucesso() {
		System.out.println("\n=== Iniciando teste: listarProdutosSucesso ===");

		// Limpar o banco antes do teste
		produtoRepository.deleteAll();
		System.out.println("Banco de dados limpo.");

		// Criar e salvar produtos
		Produto produtoA = new Produto("Produto A", 200.00, 3, "Descrição A");
		Produto produtoB = new Produto("Produto B", 300.00, 5, "Descrição B");
		produtoRepository.save(produtoA);
		produtoRepository.save(produtoB);

		// Exibir estado do banco de dados após inserção
		System.out.println("Produtos salvos no banco:");
		produtoRepository.findAll().forEach(produto -> System.out.println(" - " + produto));

		// Buscar todos os produtos
		List<Produto> produtos = produtoRepository.findAll();

		// Validar resultados
		assertFalse(produtos.isEmpty(), "A lista de produtos não deveria estar vazia");
		assertEquals(2, produtos.size(), "A lista de produtos deveria conter 2 itens");

		// Exibir os produtos encontrados
		produtos.forEach(produto -> System.out.println("Produto encontrado: " + produto));

		System.out.println("=== Fim do teste: listarProdutosSucesso ===\n");
	}

	private Produto criarProduto(ProdutoDTO produtoDTO) {
		Produto novoProduto = new Produto(produtoDTO);
		this.entityManager.persist(novoProduto);
		System.out.println("Produto persistido: " + novoProduto);
		return novoProduto;
	}
}
