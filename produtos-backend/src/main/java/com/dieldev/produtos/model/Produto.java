package com.dieldev.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.dieldev.produtos.dto.ProdutoDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@Positive(message = "O preço deve ser positivo")
	private double preco;

	@PositiveOrZero(message = "A quantidade não pode ser negativa")
	private int quantidade;

	private String descricao;

	public Produto(ProdutoDTO produtoDTO) {
		this.nome = produtoDTO.getNome();
		this.preco = produtoDTO.getPreco();
		this.quantidade = produtoDTO.getQuantidade();
		this.descricao = produtoDTO.getDescricao();
	}

	public Produto(@NotBlank(message = "O nome é obrigatório") String nome,
			@Positive(message = "O preço deve ser positivo") double preco,
			@PositiveOrZero(message = "A quantidade não pode ser negativa") int quantidade, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return String.format("Produto{id=%s, nome='%s', preco=%.2f, quantidade=%d, descricao='%s'}", id, nome, preco,
				quantidade, descricao);
	}

}
