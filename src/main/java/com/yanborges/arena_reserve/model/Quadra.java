package com.yanborges.arena_reserve.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="tb_quadras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quadra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto-incremento no banco
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // Ex: Society, Beach Tennis

    @Column(nullable = false)
    private BigDecimal precoPorHora;

    @Column(nullable = false)
    private boolean ativa = true; // Permite desativar uma quadra para manutenção


}
