package com.yanborges.arena_reserve.dto.agendamentoDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// ✨ PADRÃO DTO: Estrutura o JSON que será devolvido para o usuário de forma limpa
public record AgendamentoResponse(
        Long id,
        Long quadraId,
        String nomeQuadra,
        String nomeCliente,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        BigDecimal valorTotal,
        String status
) {}