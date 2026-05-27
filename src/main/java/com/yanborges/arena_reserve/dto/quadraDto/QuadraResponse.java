package com.yanborges.arena_reserve.dto.quadraDto;

import java.math.BigDecimal;

public record QuadraResponse(
        Long id,
        String nome,
        String tipo,
        BigDecimal precoPorHora,
        boolean ativa
) {}