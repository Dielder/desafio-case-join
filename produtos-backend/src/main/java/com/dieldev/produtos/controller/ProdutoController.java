package com.dieldev.produtos.controller;

import com.dieldev.produtos.dto.ProdutoDTO;
import com.dieldev.produtos.service.ProdutoService;
import com.dieldev.produtos.mapper.ProdutoMapper;
import com.dieldev.produtos.model.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoMapper produtoMapper;

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
		List<ProdutoDTO> produtos = produtoService.listarTodos().stream().map(produtoMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable UUID id) {
		return produtoService.buscarPorId(id).map(produto -> ResponseEntity.ok(produtoMapper.toDTO(produto)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
		// Verifica se já existe um produto com o mesmo nome
		if (produtoService.buscarPorNome(produtoDTO.getNome()).isPresent()) {
			return ResponseEntity.badRequest().body(null); // Retorna erro se o produto já existe
		}

		Produto produto = produtoMapper.toEntity(produtoDTO);
		Produto salvo = produtoService.salvar(produto);
		return ResponseEntity.ok(produtoMapper.toDTO(salvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarProduto(@PathVariable UUID id, @Valid @RequestBody ProdutoDTO produtoDTO) {
		try {
			Produto produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
			return ResponseEntity.ok(produtoMapper.toDTO(produtoAtualizado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
		if (produtoService.buscarPorId(id).isPresent()) {
			produtoService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
