package com.dieldev.produtos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ProdutoDTO {

	public ProdutoDTO(String nome, double preco, int quantidade, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}

	private UUID id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@Positive(message = "O preço deve ser positivo")
	private double preco;

	@PositiveOrZero(message = "A quantidade não pode ser negativa")
	private int quantidade;

	private String descricao;
}
