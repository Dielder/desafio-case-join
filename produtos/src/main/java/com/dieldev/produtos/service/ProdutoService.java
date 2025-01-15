package com.dieldev.produtos.service;

import com.dieldev.produtos.model.Produto;
import com.dieldev.produtos.repository.ProdutoRepository;
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
}

