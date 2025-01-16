package com.dieldev.produtos.controller;

import com.dieldev.produtos.dto.ProdutoDTO;
import com.dieldev.produtos.model.Produto;
import com.dieldev.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        List<ProdutoDTO> produtos = produtoService.listarTodos().stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable UUID id) {
        return produtoService.buscarPorId(id).map(produto -> ResponseEntity.ok(toDTO(produto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        // Verifica se já existe um produto com o mesmo nome
        if (produtoService.buscarPorNome(produtoDTO.getNome()).isPresent()) {
            return ResponseEntity.badRequest().body(null);  // Retorna erro se o produto já existe
        }
        
        Produto produto = toEntity(produtoDTO);
        Produto salvo = produtoService.salvar(produto);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable UUID id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        // Verifica se o produto com o id fornecido existe
        Optional<Produto> produtoExistente = produtoService.buscarPorId(id);
        
        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o produto não for encontrado
        }

        // Verifica se o nome do produto foi alterado e se já existe outro produto com esse nome
        Optional<Produto> produtoComNomeExistente = produtoService.buscarPorNome(produtoDTO.getNome());

        // Se existir um produto com o mesmo nome e não for o mesmo produto sendo atualizado
        if (produtoComNomeExistente.isPresent() && !produtoComNomeExistente.get().getId().equals(id)) {
            return ResponseEntity.badRequest().body("Já existe um produto com esse nome.");
        }

        // Atualiza o produto
        Produto atualizado = toEntity(produtoDTO);
        atualizado.setId(id);
        return ResponseEntity.badRequest().body("Já existe um produto com esse nome.");

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
        if (produtoService.buscarPorId(id).isPresent()) {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Métodos de conversão
    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());
        dto.setDescricao(produto.getDescricao());
        return dto;
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDescricao(dto.getDescricao());
        return produto;
    }
}
