package com.dieldev.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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
}
