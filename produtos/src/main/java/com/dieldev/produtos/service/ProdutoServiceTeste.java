//package com.dieldev.produtos.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.dieldev.produtos.model.Produto;
//import com.dieldev.produtos.repository.ProdutoRepository;
//
//class ProdutoServiceTest {
//
//	@Mock
//	private ProdutoRepository produtoRepository;
//
//	@InjectMocks
//	private ProdutoService produtoService;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this); // Inicializa os mocks
//	}
//
//	@Test
//	void salvar_DeveRetornarProdutoSalvo() {
//		// Arrange: Criar um produto de exemplo e configurar o mock
//		Produto produto = new Produto();
//		produto.setNome("Produto Teste");
//		produto.setPreco(99.99);
//		produto.setQuantidade(10);
//		produto.setDescricao("Descrição do Produto Teste");
//
//		when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
//
//		// Act: Chamar o método salvar
//		Produto resultado = produtoService.salvar(produto);
//
//		// Assert: Verificar se o produto foi salvo corretamente
//		assertEquals(produto.getNome(), resultado.getNome());
//		assertEquals(produto.getPreco(), resultado.getPreco());
//		assertEquals(produto.getQuantidade(), resultado.getQuantidade());
//		assertEquals(produto.getDescricao(), resultado.getDescricao());
//
//		// Verifica se o método save foi chamado exatamente uma vez
//		verify(produtoRepository, times(1)).save(produto);
//	}
//}
