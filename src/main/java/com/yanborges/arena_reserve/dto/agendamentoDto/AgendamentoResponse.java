package com.yanborges.arena_reserve.dto.agendamentoDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        Long quadraId,
        String nomeQuadra,
        String nomeCliente,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // 🇧🇷 Formata a saída no JSON
        LocalDateTime dataHoraInicio,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // 🇧🇷 Formata a saída no JSON
        LocalDateTime dataHoraFim,
        BigDecimal valorTotal,
        String status
) {}