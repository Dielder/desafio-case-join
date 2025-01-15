package com.dieldev.produtos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class ProdutoDTO {

    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Positive(message = "O preço deve ser positivo")
    private double preco;

    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    private int quantidade;

    private String descricao;
}
