package com.dieldev.produtos.mapper;

import com.dieldev.produtos.dto.ProdutoDTO;
import com.dieldev.produtos.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    // Converte de Produto para ProdutoDTO
    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setQuantidade(produto.getQuantidade());
        produtoDTO.setDescricao(produto.getDescricao());
        return produtoDTO;
    }

    // Converte de ProdutoDTO para Produto
    public Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setDescricao(produtoDTO.getDescricao());
        return produto;
    }
}
