package com.dieldev.produtos.service;

import com.dieldev.produtos.dto.ProdutoDTO;
import com.dieldev.produtos.model.Produto;
import com.dieldev.produtos.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> buscarPorId(UUID id) {
		return produtoRepository.findById(id);
	}

	public void deletar(UUID id) {
		produtoRepository.deleteById(id);
	}

	public Optional<Produto> buscarPorNome(String nome) {
		return produtoRepository.findByNome(nome);
	}

	// Método para verificar se já existe um produto com o mesmo nome, mas com ID
	// diferente
	public boolean verificarDuplicado(String nome, UUID id) {
		return produtoRepository.existsByNomeAndIdNot(nome, id);
	}

	// Método para criar um novo produto
	public Produto criarProduto(ProdutoDTO produtoDTO) throws Exception {
		// Verifica se já existe um produto com o mesmo nome
		if (produtoRepository.findByNome(produtoDTO.getNome()).isPresent()) {
			throw new Exception("Já existe um produto com esse nome.");
		}

		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPreco(produtoDTO.getPreco());
		produto.setQuantidade(produtoDTO.getQuantidade());

		return produtoRepository.save(produto);
	}

	// Método para atualizar um produto
	public Produto atualizarProduto(UUID id, ProdutoDTO produtoDTO) throws Exception {
		// Verifica se o produto existe
		Produto produtoExistente = produtoRepository.findById(id)
				.orElseThrow(() -> new Exception("Produto não encontrado."));

		// Verifica se já existe outro produto com o mesmo nome
		if (verificarDuplicado(produtoDTO.getNome(), id)) {
			throw new Exception("Já existe um produto com esse nome.");
		}

		// Atualiza os campos do produto existente
		produtoExistente.setNome(produtoDTO.getNome());
		produtoExistente.setDescricao(produtoDTO.getDescricao());
		produtoExistente.setPreco(produtoDTO.getPreco());
		produtoExistente.setQuantidade(produtoDTO.getQuantidade());

		// Salva as alterações
		return produtoRepository.save(produtoExistente);
	}
}
