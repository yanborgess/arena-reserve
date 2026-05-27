package com.yanborges.arena_reserve.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // ✨ PADRÃO BUILDER: Permite construir este objeto de forma fluida (ex: Agendamento.builder().id(1L).build())
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento Muitos para Um (Vários agendamentos pertencem a uma Quadra)
    @ManyToOne
    @JoinColumn(name = "quadra_id", nullable = false)
    private Quadra quadra;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private String status; // AGENDADO, CONCLUIDO, CANCELADO
}